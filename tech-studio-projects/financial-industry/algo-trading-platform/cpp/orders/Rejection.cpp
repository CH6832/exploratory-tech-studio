#include "Rejection.h"

Rejection::Rejection(const IOrderCore& rejectedOrder, RejectionReason rejectionReason)
    : _rejectedOrder(rejectedOrder), _rejectionReason(rejectionReason) {}

long Rejection::GetOrderId() const {
    return _rejectedOrder.GetOrderId();
}

long Rejection::GetUsername() const {
    return _rejectedOrder.GetUsername();
}

long Rejection::GetSecurityId() const {
    return _rejectedOrder.GetSecurityId();
}

RejectionReason Rejection::GetRejectionReason() const {
    return _rejectionReason;
}
