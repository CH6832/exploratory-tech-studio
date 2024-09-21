#include "RejectCreator.h"

// Static method implementation to generate a Rejection object.
Rejection RejectCreator::GenerateOrderRejection(const IOrderCore& rejectedOrder, RejectionReason rejectionReason) {
    return Rejection(rejectedOrder, rejectionReason);
}
