#ifndef CLIENT_BASE_H
#define CLIENT_BASE_H

#include <string>

/**
 * @class ClientBase
 * @brief Base class for VPN clients in the simulation.
 */
class ClientBase {
public:
    ClientBase(const std::string& name);
    virtual ~ClientBase();

    virtual void connect() = 0;  // Connect to the VPN
    virtual void disconnect() = 0; // Disconnect from the VPN

protected:
    std::string name_;
};

#endif // CLIENT_BASE_H
