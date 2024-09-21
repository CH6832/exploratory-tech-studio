cmake_minimum_required(VERSION 3.10)
project(credit_scoring_tool_tests)

set(CMAKE_CXX_STANDARD 11)

find_package(Boost REQUIRED COMPONENTS program_options log)

include_directories(${Boost_INCLUDE_DIRS})

add_executable(run_tests test_main.cpp ../src/logistic_regression.cpp)

target_link_libraries(run_tests ${Boost_LIBRARIES})
