set(CMAKE_TOOLCHAIN_FILE "${CMAKE_CURRENT_SOURCE_DIR}/vcpkg/scripts/buildsystems/vcpkg.cmake"
    CACHE STRING "Vcpkg toolchain file")

add_executable(your_test_executable your_test_source.cpp)
target_link_libraries(your_test_executable PRIVATE your_project_library GTest::gtest_main)

include(GoogleTest)
gtest_discover_tests(your_test_executable)

find_package(GTest CONFIG REQUIRED)
target_link_libraries(your_target_name PRIVATE GTest::gtest_main)
