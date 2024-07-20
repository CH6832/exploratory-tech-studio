// Main.cpp

#include "SegmentTree.h"
#include <iostream>
#include <vector>

int main() {
    std::vector<int> nums = {1, 3, 5, 7, 9};
    
    SegmentTree segmentTree(nums);
    
    std::cout << segmentTree.query(1, 3) << std::endl; // Output: 15
    
    segmentTree.update(2, 6);
    
    std::cout << segmentTree.query(1, 3) << std::endl; // Output: 17
    
    return 0;
}
