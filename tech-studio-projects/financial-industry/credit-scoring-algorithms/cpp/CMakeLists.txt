cmake_minimum_required(VERSION 3.10)
project(credit_scoring_tool)

set(CMAKE_CXX_STANDARD 11)

find_package(Boost REQUIRED COMPONENTS program_options log)

include_directories(${Boost_INCLUDE_DIRS})

add_executable(credit_scoring_tool src/main.cpp src/logistic_regression.cpp)

target_link_libraries(credit_scoring_tool ${Boost_LIBRARIES})
