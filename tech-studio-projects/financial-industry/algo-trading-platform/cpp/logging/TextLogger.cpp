#include "TextLoggerConfiguration.h"

// Getter for Directory
const std::string& TextLoggerConfiguration::GetDirectory() const {
    return directory;
}

// Setter for Directory
void TextLoggerConfiguration::SetDirectory(const std::string& dir) {
    directory = dir;
}

// Getter for Filename
const std::string& TextLoggerConfiguration::GetFilename() const {
    return filename;
}

// Setter for Filename
void TextLoggerConfiguration::SetFilename(const std::string& file) {
    filename = file;
}

// Getter for FileExtension
const std::string& TextLoggerConfiguration::GetFileExtension() const {
    return fileExtension;
}

// Setter for FileExtension
void TextLoggerConfiguration::SetFileExtension(const std::string& ext) {
    fileExtension = ext;
}
