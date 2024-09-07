#ifndef IORDER_CORE_H
#define IORDER_CORE_H

// Interface class for order core operations.
class IOrderCore {
public:
    // Virtual destructor for proper cleanup of derived classes.
    virtual ~IOrderCore() = default;

    // Virtual methods to be implemented by derived classes.
    virtual long GetOrderId() const = 0;
    virtual std::string GetUsername() const = 0;
    virtual int GetSecurityId() const = 0;
};

#endif // IORDER_CORE_H
