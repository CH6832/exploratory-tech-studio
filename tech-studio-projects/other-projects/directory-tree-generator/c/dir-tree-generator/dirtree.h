#ifndef DIRTREE_H
#define DIRTREE_H

// Include necessary headers
#include <sys/types.h>
#include "dirent.h"
#include <sys/stat.h>

// Define the DirectoryTree struct
typedef struct {
    char* rootDir;
    char** tree;
    int treeSize;
    int treeCapacity;
} DirectoryTree;

// Function prototypes
void DirectoryTree_init(DirectoryTree* tree, const char* rootDir);
void DirectoryTree_generate(DirectoryTree* tree);
void DirectoryTree_treeHead(DirectoryTree* tree);
void DirectoryTree_treeBody(DirectoryTree* tree, const char* directory, char* prefix);
void DirectoryTree_addDirectory(DirectoryTree* tree, const char* directory, int index, int entriesCount, char* prefix, const char* connector);
void DirectoryTree_addFile(DirectoryTree* tree, const char* file, char* prefix, const char* connector);
void DirectoryTree_addToTree(DirectoryTree* tree, const char* entry);

#endif // DIRTREE_H
