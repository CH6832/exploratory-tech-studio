#include "MatchResult.h"

// Constructor initializes the result status and details.
MatchResult::MatchResult(bool success, const std::string& details)
    : _success(success), _details(details) {}

// Getter for success status.
bool MatchResult::IsSuccess() const {
    return _success;
}

// Getter for details.
std::string MatchResult::GetDetails() const {
    return _details;
}
