#include <iostream>
#include "HM.h"

int main() {
    HashMapList hashMap;

    // Insert key-value pairs
    hashMap.insert(1, 10);
    hashMap.insert(2, 20);
    hashMap.insert(11, 30);
    hashMap.insert(12, 40);
    hashMap.insert(22, 50);

    // Display the hash map contents
    std::cout << "Hash Map Contents:" << std::endl;
    hashMap.display();

    // Retrieve values by keys
    std::cout << "Value corresponding to key 11: " << hashMap.get(11) << std::endl;
    std::cout << "Value corresponding to key 3: " << hashMap.get(3) << std::endl;

    // Remove a key-value pair
    std::cout << "Removing key 11: " << (hashMap.remove(11) ? "Success" : "Failed") << std::endl;

    // Display the hash map contents after removal
    std::cout << "Hash Map Contents after removal:" << std::endl;
    hashMap.display();

    return 0;
}
