cmake_minimum_required(VERSION 3.10)
project(ServerTest)

set(CMAKE_CXX_STANDARD 17)

# Add Catch2 header directory
include_directories(third_party/Catch2)

# Add server test executable
add_executable(server_test tests/test_server.cpp)

# No need to link Catch2 explicitly, as it is header-only

# Add CTest for running tests
enable_testing()
add_test(NAME server_test COMMAND server_test)
