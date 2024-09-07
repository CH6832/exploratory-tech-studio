#ifndef SERVER_H
#define SERVER_H

#include <iostream>
#include <string>

#ifdef _WIN32
#include <winsock2.h>
#include <ws2tcpip.h>
#include <Windows.h>
#pragma comment(lib, "ws2_32.lib")
#else
#include <sys/select.h>
#include <sys/epoll.h>
#include <unistd.h>
#endif

class Server {
public:
    Server(int port);
    ~Server();
    void start();

private:
    int create_server_socket(int port);
    void handle_client(int client_socket);
    std::string handle_request(const std::string& request);
    void log_message(const std::string& message);
    void handle_connections();

    int server_fd;

#ifdef _WIN32
    HANDLE iocp_handle; // IOCP handle for Windows
#else
    int epoll_fd; // epoll file descriptor for Linux
#endif

    const int port;
};

#endif // SERVER_H
