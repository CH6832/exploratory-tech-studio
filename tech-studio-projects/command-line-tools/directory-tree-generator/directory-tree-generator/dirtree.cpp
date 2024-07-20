#include "dirtree.h"
#include <iostream>

DirectoryTree::DirectoryTree(const std::filesystem::path& rootDir) : _rootDir(rootDir) {}

void DirectoryTree::generate() {
    _treeHead();
    _treeBody(_rootDir);
    for (const auto& entry : _tree) {
        std::cout << entry << std::endl;
    }
}

void DirectoryTree::_treeHead() {
    _tree.push_back((_rootDir / std::filesystem::path{ std::string(1, std::filesystem::path::preferred_separator) }).string());
    _tree.push_back(PIPE);
}

void DirectoryTree::_treeBody(const std::filesystem::path& directory, std::string prefix) {
    auto entries = std::vector<std::filesystem::path>{};
    std::copy(std::filesystem::directory_iterator(directory), std::filesystem::directory_iterator(), std::back_inserter(entries));
    std::sort(entries.begin(), entries.end());
    auto entriesCount = entries.size();
    for (auto index = 0; index < entriesCount; ++index) {
        auto connector = (index == entriesCount - 1) ? ELBOW : TEE;
        auto entry = entries[index];
        if (std::filesystem::is_directory(entry)) {
            _addDirectory(entry, index, entriesCount, prefix, connector);
        }
        else {
            _addFile(entry, prefix, connector);
        }
    }
}

void DirectoryTree::_addDirectory(const std::filesystem::path& directory, int index, int entriesCount, std::string& prefix, const std::string& connector) {
    _tree.push_back(prefix + connector + " " + directory.filename().string() + std::string(1, std::filesystem::path::preferred_separator));
    if (index != entriesCount - 1) {
        prefix += PIPE_PREFIX;
    }
    else {
        prefix += SPACE_PREFIX;
    }
    _treeBody(directory, prefix);
    _tree.push_back(prefix.erase(prefix.size() - PIPE_PREFIX.size()));
}

void DirectoryTree::_addFile(const std::filesystem::path& file, const std::string& prefix, const std::string& connector) {
    _tree.push_back(prefix + connector + " " + file.filename().string());
}
