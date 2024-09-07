# Package Management System - Draft

## :newspaper: About the project

The Package Management System is a command line tool designed to manage packages stored as zip archives. It provides functionalities to install, uninstall, and reset packages, ensuring smooth package management operations.

### How it works

1. Run the script `main.cpp` with appropriate commands to perform package management tasks. For example, `packages-manager.exe install package_id` installs a package (zip archive), `packages-manager.exe uninstall package_id` uninstalls a package, and `packages-manager.exe reset` resets the package storage.

2. The system manages package information stored in the `pkgs.json` file, which includes package IDs, URLs, and installation directories.

### Content overview

    .
    ├── packages-manager/ - package manager project
    ├── pkgs_installed/ - folder to store installed packages
    ├── storage/ - folder to store package archives
    ├── tests/ - contains testcases
    ├── vcpkg/ - contains vcpkg package manager
    ├── tests/ - folder to store the installed packages
    ├── .gitignore - ignore files, folders, etc. for version control
    ├── CMakeLists.txt - configurations for cmake buildsystem
    ├── CMakePresets.json - settings for cmake buildsystem
    ├── COPYRIGHT - project copyright
    └── README.md - relevant information about the project

## :notebook: Features

* Install packages from storage
* Uninstall installed packages
* Reset package storage
* Logging available

## :runner: Getting started

### Prerequisites

0. Clone the project:

```sh
git clone https://github.com/CH6832/packages-manager.git
```

1. Navigate into root folder:

```sh
cd packages-manager
```

### Usage

2. Run the script `main.cpp` with appropriate commands.

## :books: Resources used to create this project

* CPlusPlus
  * [C++ Programming Language](https://devdocs.io/cpp/)
  * [Standard C++ Library reference](https://cplusplus.com/reference/)
  * [C++ reference](https://en.cppreference.com/w/)
  * [CMake Documentation and Community](https://cmake.org/documentation/)
* Editor
  * [Visual Studio Community Edition](https://code.visualstudio.com/)
* Markdwon
  * [Basic syntax](https://www.markdownguide.org/basic-syntax/)
  * [Complete list of github markdown emofis](https://dev.to/nikolab/complete-list-of-github-markdown-emoji-markup-5aia)
  * [Awesome template](http://github.com/Human-Activity-Recognition/blob/main/README.md)
  * [.gitignore file](https://git-scm.com/docs/gitignore)

## :bookmark: License

This project is licensed under the terms of the [MIT License](LICENSE).

## :copyright: Copyright

See the [LICENSE](LICENSE) file for copyright and licensing details.
