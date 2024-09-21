#include "RejectionReason.h"

// Constructor initializes the rejection reason.
RejectionReason::RejectionReason(const std::string& reason)
    : _reason(reason) {}

// Getter for the reason.
std::string RejectionReason::GetReason() const {
    return _reason;
}

// Comparison operator.
bool RejectionReason::operator==(const RejectionReason& other) const {
    return _reason == other._reason;
}
