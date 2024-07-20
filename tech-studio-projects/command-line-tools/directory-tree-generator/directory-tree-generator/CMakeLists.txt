# CMakeList.txt : CMake project for directory-tree-generator, include source and define
# project specific logic here.
#

# Add source to this project's executable.
add_executable (directory-tree-generator "dirtree.cpp" "dirtree.h" "main.cpp" "cli.h" "cli.cpp")

if (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(TARGET directory-tree-generator PROPERTY CXX_STANDARD 20)
endif()

# TODO: Add tests and install targets if needed.
