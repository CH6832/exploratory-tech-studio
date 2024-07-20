int main() {
    // Example: Create a binary search tree and perform insertions
    BinarySearchTree bst;
    int keys[] = {10, 5, 15, 3, 7, 12, 17};
    for (int key : keys) {
        bst.insert(key);
    }

    // Perform inorder traversal to print keys in sorted order
    std::cout << "Inorder traversal of the binary search tree:" << std::endl;
    bst.inorderTraversal();

    // Search for a key in the binary search tree
    int searchKey = 7;
    BinarySearchTree::TreeNode* result = bst.search(searchKey);
    if (result != nullptr) {
        std::cout << "Key " << searchKey << " found in the binary search tree." << std::endl;
    } else {
        std::cout << "Key " << searchKey << " not found in the binary search tree." << std::endl;
    }

    return 0;
}
