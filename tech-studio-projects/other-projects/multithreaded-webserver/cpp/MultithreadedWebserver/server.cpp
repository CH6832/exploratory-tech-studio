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

void Server::start() {
    server_fd = create_server_socket(port);
    if (server_fd < 0) return;

    log_message("Server is listening on port " + std::to_string(port));
    handle_connections();
}
