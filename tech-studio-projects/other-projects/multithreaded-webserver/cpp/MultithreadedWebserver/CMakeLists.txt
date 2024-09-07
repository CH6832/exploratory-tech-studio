# CMakeList.txt : CMake project for CMakeProject1, include source and define
# project specific logic here.
#

# Add source to this project's executable.
add_executable (MultithreadedWebserver "main.cpp"  "server.hpp" "server.cpp" "thread_pool.hpp" "thread_pool.cpp" "../tests/test_server.cpp")

if (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(TARGET MultithreadedWebserver PROPERTY CXX_STANDARD 20)
endif()

# TODO: Add tests and install targets if needed.
