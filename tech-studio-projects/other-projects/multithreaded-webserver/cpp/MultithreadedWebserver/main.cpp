#include "server.hpp"

int main() {
    Server server(8088);
    server.start();
    return 0;
}
