# Integrate vcpkg
set(CMAKE_TOOLCHAIN_FILE "${CMAKE_CURRENT_SOURCE_DIR}/vcpkg/scripts/buildsystems/vcpkg.cmake" CACHE STRING "Vcpkg toolchain file")

# Add source to this project's executable.
add_executable (packages-manager "PackagesManager.cpp" "PackagesManager.h" "main.cpp")

if (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(TARGET packages-manager PROPERTY CXX_STANDARD 20)
endif()

# Find and link against the JSON library if installed via vcpkg
find_package(jsoncpp CONFIG REQUIRED)
target_link_libraries(packages-manager PRIVATE jsoncpp_lib)
