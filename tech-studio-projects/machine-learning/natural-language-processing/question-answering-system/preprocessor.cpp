#include <iostream>
#include <string>

std::string preprocess(const std::string& text) {
    // Example preprocessing: converting to lowercase
    std::string result;
    for (char c : text) {
        result += tolower(c);
    }
    return result;
}

extern "C" {
    const char* preprocess_text(const char* text) {
        std::string processed = preprocess(text);
        return processed.c_str();
    }
}
