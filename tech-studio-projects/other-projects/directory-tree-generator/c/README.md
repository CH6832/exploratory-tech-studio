# Directory Tree Generator

## :newspaper: About the Project

A simple directory tree generator written in C.

> [!NOTE]  
> **NOTE:** This program code has been adapted, simplified, and provided with references to data types and comments. The original code, which inspired this project, can be viewed [here](https://realpython.com/directory-tree-generator-python/).

### How It Works

The program accepts a directory path as input and recursively iterates over the folder to display a directory tree diagram.

### Content Overview

    .
    ├── directory-tree-generator/    - Program logic for the directory tree generator
    ├── out/                         - Test directory for the program
    ├── .gitignore                    - List of files and folders ignored by git
    ├── CMakeLists.txt               - CMake configuration file
    ├── CMakePresets.json            - Define presets for CMake projects
    ├── COPYRIGHT                    - Project copyright
    ├── LICENSE                      - License text
    └── README.md                    - Contains project information

## :runner: Getting Started

1. Clone the repository:

    ```sh
    git clone https://github.com/CH6832/directory-tree-generator.git
    ```

2. Navigate into the root folder:

    ```sh
    cd directory-tree-generator
    ```

3. Build the program using CMake:

    ```sh
    cmake -B build
    cmake --build build
    ```

4. Run the executable:

    ```sh
    ./build/directory-tree-generator out
    ```

   Replace `out` with the path to any directory you wish to visualize.

## :books: Resources Used to Create This Project

* **C Programming Language**
  * [C Programming Language Documentation](https://devdocs.io/c/)
  * [Standard C Library Reference](https://en.cppreference.com/w/c)
  * [CMake Documentation and Community](https://cmake.org/documentation/)
* **Editor**
  * [Visual Studio Code](https://code.visualstudio.com/)
  * [GCC](https://gcc.gnu.org/) or [Clang](https://clang.llvm.org/)
