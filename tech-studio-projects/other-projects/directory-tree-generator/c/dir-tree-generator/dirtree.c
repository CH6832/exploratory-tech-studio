#include "dirtree.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define PIPE "|"
#define TEE "|-- "
#define ELBOW "`-- "
#define PIPE_PREFIX "|   "
#define SPACE_PREFIX "    "

void DirectoryTree_init(DirectoryTree* tree, const char* rootDir) {
    tree->rootDir = strdup(rootDir);
    tree->treeSize = 0;
    tree->treeCapacity = 10;
    tree->tree = (char**)malloc(tree->treeCapacity * sizeof(char*));
}

void DirectoryTree_generate(DirectoryTree* tree) {
    DirectoryTree_treeHead(tree);
    DirectoryTree_treeBody(tree, tree->rootDir, "");
    for (int i = 0; i < tree->treeSize; ++i) {
        printf("%s\n", tree->tree[i]);
        free(tree->tree[i]);
    }
    free(tree->tree);
    free(tree->rootDir);
}

void DirectoryTree_treeHead(DirectoryTree* tree) {
    char path[1024];
    sprintf(path, "%s%c", tree->rootDir, '/');
    DirectoryTree_addToTree(tree, path);
    DirectoryTree_addToTree(tree, PIPE);
}

// Comparison function for sorting directory entries alphabetically
int custom_alphasort(const struct dirent** a, const struct dirent** b) {
    return _stricmp((*a)->d_name, (*b)->d_name);
}

void DirectoryTree_treeBody(DirectoryTree* tree, const char* directory, char* prefix) {
    struct dirent** entries;
    int entriesCount = scandir(directory, &entries, NULL, custom_alphasort);
    if (entriesCount < 0) {
        perror("scandir");
        return;
    }

    for (int index = 0; index < entriesCount; ++index) {
        struct dirent* entry = entries[index];
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) {
            free(entry);
            continue;
        }

        char fullPath[1024];
        snprintf(fullPath, sizeof(fullPath), "%s/%s", directory, entry->d_name);

        struct stat st;
        if (stat(fullPath, &st) == -1) {
            perror("stat");
            free(entry);
            continue;
        }

        const char* connector = (index == entriesCount - 1) ? ELBOW : TEE;

        if (S_ISDIR(st.st_mode)) {
            DirectoryTree_addDirectory(tree, fullPath, index, entriesCount, prefix, connector);
        }
        else {
            DirectoryTree_addFile(tree, fullPath, prefix, connector);
        }
        free(entry);
    }
    free(entries);
}

void DirectoryTree_addDirectory(DirectoryTree* tree, const char* directory, int index, int entriesCount, char* prefix, const char* connector) {
    char entry[1024];
    sprintf(entry, "%s%s %s/", prefix, connector, strrchr(directory, '/') + 1);
    DirectoryTree_addToTree(tree, entry);

    char newPrefix[1024];
    strcpy(newPrefix, prefix);
    strcat(newPrefix, (index != entriesCount - 1) ? PIPE_PREFIX : SPACE_PREFIX);

    DirectoryTree_treeBody(tree, directory, newPrefix);
}

void DirectoryTree_addFile(DirectoryTree* tree, const char* file, char* prefix, const char* connector) {
    char entry[1024];
    sprintf(entry, "%s%s %s", prefix, connector, strrchr(file, '/') + 1);
    DirectoryTree_addToTree(tree, entry);
}

void DirectoryTree_addToTree(DirectoryTree* tree, const char* entry) {
    if (tree->treeSize >= tree->treeCapacity) {
        tree->treeCapacity *= 2;
        tree->tree = (char**)realloc(tree->tree, tree->treeCapacity * sizeof(char*));
    }
    tree->tree[tree->treeSize++] = strdup(entry);
}
