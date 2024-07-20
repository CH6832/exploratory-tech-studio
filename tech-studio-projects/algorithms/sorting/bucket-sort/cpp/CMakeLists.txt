cmake_minimum_required(VERSION 3.10)

# Project name
project(BucketSort)

# Set C++ standard
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED True)

# Include directories
include_directories(${PROJECT_SOURCE_DIR})

# Add the executable
add_executable(BucketSort main.cpp BucketSort.cpp)
