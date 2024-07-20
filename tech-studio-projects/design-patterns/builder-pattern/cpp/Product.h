#ifndef PRODUCT_H
#define PRODUCT_H

#include <string>
#include <iostream>

class Product {
public:
    Product(const std::string& part1 = "", const std::string& part2 = "", const std::string& part3 = "")
        : part1_(part1), part2_(part2), part3_(part3) {}

    void setPart1(const std::string& part1) { part1_ = part1; }
    void setPart2(const std::string& part2) { part2_ = part2; }
    void setPart3(const std::string& part3) { part3_ = part3; }

    void display() const {
        std::cout << "Part 1: " << part1_ << ", Part 2: " << part2_ << ", Part 3: " << part3_ << std::endl;
    }

private:
    std::string part1_;
    std::string part2_;
    std::string part3_;
};

#endif // PRODUCT_H
