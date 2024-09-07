#pragma once

#include <string>

// Class to hold text logger configuration details.
class TextLoggerConfiguration {
public:
    // Constructor
    TextLoggerConfiguration() = default;

    // Getter and setter for the Directory
    const std::string& GetDirectory() const;
    void SetDirectory(const std::string& directory);

    // Getter and setter for the Filename
    const std::string& GetFilename() const;
    void SetFilename(const std::string& filename);

    // Getter and setter for the FileExtension
    const std::string& GetFileExtension() const;
    void SetFileExtension(const std::string& fileExtension);

private:
    std::string directory;
    std::string filename;
    std::string fileExtension;
};
