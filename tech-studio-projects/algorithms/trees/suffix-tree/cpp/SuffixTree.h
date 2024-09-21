#include "SuffixTreeNode.h"
#include <iostream>
#include <unordered_map>

class SuffixTree {
private:
    SuffixTreeNode* root;
    std::string text;

    void buildSuffixTree() {
        int n = text.length();
        SuffixTreeNode* activeNode = root;
        int activeEdge = 0;
        int activeLength = 0;
        int remainingSuffixCount = 0;
        SuffixTreeNode* lastNewNode = nullptr;

        for (int i = 0; i < n; i++) {
            lastNewNode = nullptr;
            remainingSuffixCount++;

            while (remainingSuffixCount > 0) {
                if (activeLength == 0) {
                    activeEdge = i;
                }

                if (activeNode->children.find(text[i]) == activeNode->children.end()) {
                    activeNode->children[text[i]] = new SuffixTreeNode(i, new int(n - 1));

                    if (lastNewNode != nullptr) {
                        lastNewNode->suffixLink = activeNode;
                        lastNewNode = nullptr;
                    }
                } else {
                    SuffixTreeNode* nextNode = activeNode->children[text[activeEdge]];

                    if (activeLength >= *nextNode->end - nextNode->start + 1) {
                        activeEdge += *nextNode->end - nextNode->start + 1;
                        activeLength -= *nextNode->end - nextNode->start + 1;
                        activeNode = nextNode;
                        continue;
                    }

                    if (text[nextNode->start + activeLength] == text[i]) {
                        if (lastNewNode != nullptr && activeNode != root) {
                            lastNewNode->suffixLink = activeNode;
                            lastNewNode = nullptr;
                        }

                        activeLength++;
                        break;
                    }

                    SuffixTreeNode* newNode  = new SuffixTreeNode(nextNode->start, new int(nextNode->start + activeLength - 1));
                    activeNode->children[text[activeEdge]] = newNode;
                    newNode->children[text[i]] = new SuffixTreeNode(i, new int(n - 1));
                    nextNode->start += activeLength;
                    newNode->children[text[nextNode->start]] = nextNode;

                    if (lastNewNode != nullptr) {
                        lastNewNode->suffixLink = newNode;
                    }

                    lastNewNode = newNode;
                }

                remainingSuffixCount--;

                if (activeNode == root && activeLength > 0) {
                    activeLength--;
                    activeEdge = i - remainingSuffixCount + 1;
                } else if (activeNode != root) {
                    activeNode = activeNode->suffixLink;
                }
            }
        }
    }

    void traverse(SuffixTreeNode* node, std::string suffix) {
        if (node->start != -1) {
            std::cout << suffix + text.substr(node->start, *node->end + 1 - node->start) << std::endl;
        }

        for (auto const& child : node->children) {
            traverse(child.second, suffix + text.substr(node->start, *node->end + 1 - node->start));
        }
    }

public:
    SuffixTree(std::string text) {
        this->root = new SuffixTreeNode(-1, nullptr);
        this->text = text;
        buildSuffixTree();
    }

    void printSuffixes() {
        traverse(root, "");
    }
};
