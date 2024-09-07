# CMakeList.txt : CMake project for dir-tree-generator, include source and define
# project specific logic here.
#

# Add source to this project's executable.
add_executable (dir-tree-generator "main.c"  "cli.h" "cli.c" "dirtree.c" "dirent.h")

if (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(TARGET dir-tree-generator PROPERTY CXX_STANDARD 20)
endif()

# TODO: Add tests and install targets if needed.
