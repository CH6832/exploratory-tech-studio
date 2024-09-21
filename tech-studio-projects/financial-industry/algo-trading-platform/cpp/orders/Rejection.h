#pragma once

#include "IOrderCore.h"
#include "RejectionReason.h"

// Class representing a rejection.
class Rejection {
public:
    // Constructor.
    Rejection(const IOrderCore& rejectedOrder, RejectionReason rejectionReason);

    // Getters for the properties.
    long GetOrderId() const;
    long GetUsername() const;
    long GetSecurityId() const;
    RejectionReason GetRejectionReason() const;

private:
    const IOrderCore& _rejectedOrder;
    RejectionReason _rejectionReason;
};
