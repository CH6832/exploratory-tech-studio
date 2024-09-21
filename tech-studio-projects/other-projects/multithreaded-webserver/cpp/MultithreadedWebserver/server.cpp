#include "server.hpp"
#include "thread_pool.hpp"
#include <iostream>
#include <fstream>
#include <sstream>
#include <cstring>
#ifdef _WIN32
#include <windows.h>
#else
#include <unistd.h>   // For Unix system calls and functions
#endif
#include <sys/types.h>
#ifdef _WIN32
#include <winsock2.h>  // Windows Sockets API
#include <ws2tcpip.h>  // For additional TCP/IP functions
#pragma comment(lib, "Ws2_32.lib")  // Link against the Winsock library
#else
#include <sys/types.h>  // For types used by socket functions
#include <sys/socket.h> // For socket-related functions
#include <netinet/in.h> // For Internet address family
#include <arpa/inet.h>  // For IP address conversion
#include <unistd.h>     // For close() function
#include <sys/epoll.h>
#endif
#include <fcntl.h>
#include <mutex>
#include <chrono>
#include <mutex>
#include <thread>
#include <unordered_map>
#include "token_bucket.hpp"
#include <iostream>
#include "curl/curl.h"  // Libcurl for HTTP requests
#include <openssl/ssl.h>       // For SSL-related functions and types
#include <openssl/err.h>       // For error handling functions
#include <openssl/opensslv.h>  // OpenSSL version information

std::mutex log_mutex;

Server::Server(int port) : port(port), server_fd(-1)
#ifdef _WIN32
// On Windows, epoll_fd is not used, so no initialization needed.
#else
, epoll_fd(-1) // Initialize epoll_fd only on Linux/Unix
#endif
{}

Server::~Server() {
#ifdef _WIN32
    if (server_fd != INVALID_SOCKET) closesocket(server_fd);
    // epoll_fd is not applicable on Windows, so don't use it
    WSACleanup(); // Clean up Winsock
#else
    if (server_fd >= 0) close(server_fd);
    if (epoll_fd >= 0) close(epoll_fd); // Close epoll_fd only on Linux/Unix
#endif
}

int Server::create_server_socket(int port) {
    // Initialize Winsock
    WSADATA wsaData;
    int result = WSAStartup(MAKEWORD(2, 2), &wsaData);
    if (result != 0) {
        log_message("WSAStartup failed");
        return -1;
    }

    int fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd == INVALID_SOCKET) {
        log_message("Socket creation failed");
        WSACleanup();
        return -1;
    }

    // Set the socket to non-blocking mode
    u_long mode = 1; // 1 to enable non-blocking mode
    result = ioctlsocket(fd, FIONBIO, &mode);
    if (result != NO_ERROR) {
        log_message("Failed to set non-blocking mode");
        closesocket(fd);
        WSACleanup();
        return -1;
    }

    sockaddr_in address;
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(port);

    if (bind(fd, (struct sockaddr*)&address, sizeof(address)) == SOCKET_ERROR) {
        log_message("Bind failed");
        closesocket(fd);
        WSACleanup();
        return -1;
    }

    if (listen(fd, 10) == SOCKET_ERROR) {
        log_message("Listen failed");
        closesocket(fd);
        WSACleanup();
        return -1;
    }

    return fd;
}

void Server::log_message(const std::string& message) {
    std::lock_guard<std::mutex> guard(log_mutex);
    std::cout << message << std::endl;
}

std::string Server::handle_request(const std::string& request) {
    std::istringstream request_stream(request);
    std::string method, path, version;
    request_stream >> method >> path >> version;

    if (method == "GET") {
        if (path == "/") path = "/index.html"; // Default file
        path = "." + path; // Relative path to the current directory

        std::ifstream file(path, std::ios::binary);
        if (file) {
            std::ostringstream contents;
            contents << file.rdbuf();
            std::string body = contents.str();
            std::string response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: " + std::to_string(body.size()) + "\r\n\r\n" + body;
            return response;
        }
        else {
            return "HTTP/1.1 404 Not Found\r\n\r\n";
        }
    }
    else {
        return "HTTP/1.1 400 Bad Request\r\n\r\n";
    }
}

// Platform-independent log function
void log_message(const std::string& message) {
    std::cout << message << std::endl;
}

// Platform-independent request handler function
std::string handle_request(const std::string& request) {
    // Process the request and return a response
    return "Processed: " + request;
}

void Server::handle_client(int client_socket) {
    char buffer[1024] = { 0 };

#ifdef _WIN32
    // Use int for bytes_read on Windows
    int bytes_read = recv(client_socket, buffer, sizeof(buffer) - 1, 0);
#else
    // Use ssize_t for bytes_read on Unix/Linux
    ssize_t bytes_read = read(client_socket, buffer, sizeof(buffer) - 1);
#endif

    if (bytes_read > 0) {
        buffer[bytes_read] = '\0'; // Null-terminate the string
        std::string request(buffer);
        log_message("Received request:\n" + request);

        std::string response = handle_request(request);

#ifdef _WIN32
        send(client_socket, response.c_str(), response.size(), 0);
#else
        write(client_socket, response.c_str(), response.size());
#endif
    }
    else {
        log_message("Failed to read from client socket");
    }

#ifdef _WIN32
    closesocket(client_socket);
#else
    close(client_socket);
#endif
}

void Server::handle_connections() {
    // Create an IOCP
    HANDLE iocp = CreateIoCompletionPort(INVALID_HANDLE_VALUE, NULL, 0, 0);
    if (iocp == NULL) {
        log_message("Failed to create IOCP");
        return;
    }

    // Associate the server socket with the IOCP
    if (CreateIoCompletionPort((HANDLE)server_fd, iocp, (ULONG_PTR)server_fd, 0) == NULL) {
        log_message("Failed to associate server socket with IOCP");
        return;
    }

    // Thread pool to handle IO completions
    ThreadPool thread_pool(4);

    while (true) {
        DWORD bytesTransferred;
        ULONG_PTR completionKey;
        OVERLAPPED* overlapped = nullptr;

        BOOL result = GetQueuedCompletionStatus(iocp, &bytesTransferred, &completionKey, &overlapped, INFINITE);
        if (!result) {
            log_message("Failed in GetQueuedCompletionStatus");
            continue;
        }

        SOCKET client_socket = (SOCKET)completionKey;

        // Check if it's a new connection
        if (client_socket == server_fd) {
            // Accept new connections
            while (true) {
                SOCKET client_socket = accept(server_fd, nullptr, nullptr);
                if (client_socket == INVALID_SOCKET) {
                    if (WSAGetLastError() == WSAEWOULDBLOCK) break;
                    log_message("Accept failed");
                    continue;
                }

                // Associate the new client socket with IOCP
                if (CreateIoCompletionPort((HANDLE)client_socket, iocp, (ULONG_PTR)client_socket, 0) == NULL) {
                    log_message("Failed to associate client socket with IOCP");
                    closesocket(client_socket);
                }
            }
        }
        else {
            // Handle client request
            thread_pool.enqueue([this, client_socket]() {
                handle_client(client_socket);
                closesocket(client_socket); // Close socket after handling
                });
        }
    }
}

std::string get_mime_type(const std::string& path) {
    if (path.ends_with(".html")) return "text/html";
    else if (path.ends_with(".css")) return "text/css";
    else if (path.ends_with(".js")) return "application/javascript";
    else if (path.ends_with(".jpg") || path.ends_with(".jpeg")) return "image/jpeg";
    else if (path.ends_with(".png")) return "image/png";
    else return "application/octet-stream"; // Default binary type
}

std::string Server::handle_static_file(const std::string& path) {
    std::ifstream file("." + path, std::ios::binary);
    if (file) {
        std::ostringstream contents;
        contents << file.rdbuf();
        std::string body = contents.str();
        std::string mime_type = get_mime_type(path);
        return "HTTP/1.1 200 OK\r\nContent-Type: " + mime_type + "\r\nContent-Length: " + std::to_string(body.size()) + "\r\n\r\n" + body;
    }
    return "HTTP/1.1 404 Not Found\r\n\r\n";
}

SSL_CTX* create_ssl_context() {
    SSL_CTX* ctx = SSL_CTX_new(TLS_server_method());
    if (!ctx) {
        log_message("Unable to create SSL context");
        return nullptr;
    }
    SSL_CTX_set_ecdh_auto(ctx, 1);
    // Load certificate and private key
    if (SSL_CTX_use_certificate_file(ctx, "cert.pem", SSL_FILETYPE_PEM) <= 0 ||
        SSL_CTX_use_PrivateKey_file(ctx, "key.pem", SSL_FILETYPE_PEM) <= 0) {
        log_message("Failed to load cert or key");
        return nullptr;
    }
    return ctx;
}

// Usage in your server
std::unordered_map<std::string, TokenBucket> rate_limiters;

// Initialize token bucket per client IP or API key
void initialize_rate_limiters() {
    rate_limiters["client_ip_1"] = TokenBucket(5, 10); // 5 requests per second, burst of 10
    rate_limiters["client_ip_2"] = TokenBucket(2, 5);  // 2 requests per second, burst of 5
}

// Check if the client request should be allowed
bool check_rate_limit(const std::string& client_ip) {
    if (rate_limiters[client_ip].allow()) {
        return true;  // Allow request
    }
    return false;  // Deny or throttle request
}

void Server::handle_client(int client_socket) {
    std::string client_ip = get_client_ip(client_socket);  // Function to retrieve client's IP

    if (!check_rate_limit(client_ip)) {
        std::string response = "HTTP/1.1 429 Too Many Requests\r\n\r\n";
        send(client_socket, response.c_str(), response.size(), 0);
        close_socket(client_socket);
        return;
    }

    // Process the request as usual
    char buffer[1024] = { 0 };
    ssize_t bytes_read = read(client_socket, buffer, sizeof(buffer) - 1);
    // ...
}

void register_service_with_consul(const std::string& service_name, int port) {
    CURL* curl = curl_easy_init();
    if (curl) {
        std::string consul_url = "http://localhost:8500/v1/agent/service/register";

        std::string service_json = R"({
            "ID": ")" + service_name + R"(",
            "Name": ")" + service_name + R"(",
            "Address": "127.0.0.1",
            "Port": )" + std::to_string(port) + R"(,
            "Tags": ["primary"]
        })";

        curl_easy_setopt(curl, CURLOPT_URL, consul_url.c_str());
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, service_json.c_str());
        curl_easy_setopt(curl, CURLOPT_POST, 1L);
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, curl_slist_append(NULL, "Content-Type: application/json"));

        CURLcode res = curl_easy_perform(curl);
        if (res != CURLE_OK) {
            std::cerr << "Failed to register service with Consul: " << curl_easy_strerror(res) << std::endl;
        }
        else {
            std::cout << "Service registered with Consul successfully." << std::endl;
        }
        curl_easy_cleanup(curl);
    }
}

std::string discover_service(const std::string& service_name) {
    CURL* curl = curl_easy_init();
    std::string service_url = "http://localhost:8500/v1/catalog/service/" + service_name;
    std::string response;

    if (curl) {
        curl_easy_setopt(curl, CURLOPT_URL, service_url.c_str());
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, [](char* data, size_t size, size_t nmemb, std::string* buffer) {
            buffer->append(data, size * nmemb);
            return size * nmemb;
            });
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
        curl_easy_perform(curl);
        curl_easy_cleanup(curl);
    }

    // Parse the response for service details
    return response;
}

void Server::start() {
    server_fd = create_server_socket(port);
    if (server_fd < 0) return;

    log_message("Server is listening on port " + std::to_string(port));
    handle_connections();
}
