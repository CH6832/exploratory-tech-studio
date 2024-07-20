#ifndef DIRTREE_HPP
#define DIRTREE_HPP

#include <string>
#include <filesystem>
#include <vector>
#include <algorithm>

class DirectoryTree {
public:
    DirectoryTree(const std::filesystem::path& rootDir);

    void generate();

private:
    std::filesystem::path _rootDir;
    std::vector<std::string> _tree;
    const std::string PIPE = "|";
    const std::string ELBOW = "`--";
    const std::string TEE = "|--";
    const std::string PIPE_PREFIX = "|   ";
    const std::string SPACE_PREFIX = "    ";

    void _treeHead();

    void _treeBody(const std::filesystem::path& directory, std::string prefix = "");

    void _addDirectory(const std::filesystem::path& directory, int index, int entriesCount, std::string& prefix, const std::string& connector);

    void _addFile(const std::filesystem::path& file, const std::string& prefix, const std::string& connector);
};

#endif // DIRTREE_HPP
