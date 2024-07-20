#include "gtest/gtest.h"
#include "../Singleton.h"

// Test case for Singleton class
TEST(SingletonTest, InstanceTest) {
    // Get the instance of Singleton
    Singleton* singleton1 = Singleton::getInstance();
    Singleton* singleton2 = Singleton::getInstance();

    // Check if both pointers point to the same instance
    EXPECT_EQ(singleton1, singleton2);

    // Cleanup (optional)
    delete singleton1;
}

// Test case for destruction of Singleton instance
TEST(SingletonTest, DestructionTest) {
    // Get the instance of Singleton
    Singleton* singleton = Singleton::getInstance();

    // Delete the instance
    delete singleton;

    // Get the instance again
    Singleton* newSingleton = Singleton::getInstance();

    // Check if the new instance is different from the deleted one
    EXPECT_NE(singleton, newSingleton);
}

// Main function to run tests
int main(int argc, char** argv) {
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
