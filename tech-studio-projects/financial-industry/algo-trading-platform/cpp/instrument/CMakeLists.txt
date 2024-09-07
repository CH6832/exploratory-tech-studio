cmake_minimum_required(VERSION 3.8)

# Define an interface library for logging
add_library(instrument INTERFACE)

# Specify include directories for the logging library
target_include_directories(instrument INTERFACE
    ${CMAKE_CURRENT_SOURCE_DIR}/include
)
