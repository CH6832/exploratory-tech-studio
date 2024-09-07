#pragma once

#include <string>

// Class for rejection reasons with additional details.
class RejectionReason {
public:
    // Constructor.
    explicit RejectionReason(const std::string& reason);

    // Getter for the reason.
    std::string GetReason() const;

    // Comparison operator.
    bool operator==(const RejectionReason& other) const;

private:
    std::string _reason;  // Reason for the rejection.
};
