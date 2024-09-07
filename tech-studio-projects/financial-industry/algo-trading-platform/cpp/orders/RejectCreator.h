#pragma once

#include "Rejection.h"
#include "IOrderCore.h"
#include "RejectionReason.h"

// Class for creating rejection instances.
class RejectCreator {
public:
    // Static method to generate a Rejection object.
    static Rejection GenerateOrderRejection(const IOrderCore& rejectedOrder, RejectionReason rejectionReason);
};
