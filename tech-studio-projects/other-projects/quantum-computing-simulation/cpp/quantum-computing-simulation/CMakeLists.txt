# CMakeList.txt : CMake project for quantum-computing-simulation, include source and define
# project specific logic here.
#

# Add source to this project's executable.
add_executable (quantum-computing-simulation "main.cpp" "Qubit.cpp" "Qubit.h")

if (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(TARGET quantum-computing-simulation PROPERTY CXX_STANDARD 20)
endif()

# TODO: Add tests and install targets if needed.
