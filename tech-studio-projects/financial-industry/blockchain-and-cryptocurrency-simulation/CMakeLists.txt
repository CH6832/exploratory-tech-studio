### **CMakeLists.txt**

Update your `CMakeLists.txt` to include the tests. Here’s an example snippet to add to your `CMakeLists.txt`:

```cmake
cmake_minimum_required(VERSION 3.10)
project(BlockchainProject)

set(CMAKE_CXX_STANDARD 17)

# Find OpenSSL
find_package(OpenSSL REQUIRED)

# Include directories
include_directories(include ${OpenSSL_INCLUDE_DIR})

# Source files
set(SOURCES
    src/Blockchain.cpp
    src/Block.cpp
    src/NetworkNode.cpp
    src/ProofOfStake.cpp
    src/ProofOfWork.cpp
    src/Transaction.cpp
)

# Executable
add_executable(blockchain main.cpp ${SOURCES})

# Link OpenSSL
target_link_libraries(blockchain ${OpenSSL_LIBRARIES})

# Tests
enable_testing()
find_package(GTest REQUIRED)
include_directories(${GTEST_INCLUDE_DIRS})

set(TEST_SOURCES
    tests/BlockchainTests.cpp
    tests/BlockTests.cpp
    tests/NetworkNodeTests.cpp
    tests/ProofOfStakeTests.cpp
    tests/ProofOfWorkTests.cpp
    tests/TransactionTests.cpp
)

add_executable(tests ${TEST_SOURCES} ${SOURCES})
target_link_libraries(tests ${GTEST_LIBRARIES} pthread)

add_test(NAME AllTests COMMAND tests)
