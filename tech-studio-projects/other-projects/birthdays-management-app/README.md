# Birthdays Manager

## About the project

Birthday Manager is a simple web application built with Flask that allows users to add and manage birthday entries in a database. Users can add names along with their corresponding birth months and days, and view all stored entries.

## Table of Contents
- [Birthdays Manager](#birthdays-manager)
  - [About the project](#about-the-project)
  - [Table of Contents](#table-of-contents)
    - [Directory directory structure](#directory-directory-structure)
  - [Features](#features)
  - [Getting started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Run the application](#run-the-application)
    - [Read the docs](#read-the-docs)
    - [Run the application in a Docker container:](#run-the-application-in-a-docker-container)
    - [Build the docs](#build-the-docs)
    - [Update database](#update-database)
  - [Resources used to create this project](#resources-used-to-create-this-project)
  - [License](#bookmark-license)
  - [Copyright](#copyright-copyright)

### Directory directory structure

    .
    ├── COPYRIGHT - project copyright
    ├── docs/ - documentation sources
    ├── instance/ - database
    ├── migrations/ - generated migrations scripts byFlask-Migrate. Files in there should be deleted when database has to be generated from scratch.
    ├── static/ - contains static files (css, js)
    ├── templates/ - contains html templates
    ├── tests/ - all kinds of test scripts
    ├── app.py - flask application and program entry
    ├── LICENSE - license file
    ├── README.md - project descriptions and instructions
    └── requirements.txt - project requirements

## Features

* Add and delete new birthday entries.
* View all stored birthday entries.
* Simple and intuitive user interface.

## Getting started

### Prerequisites

0. Clone the repository:

```sh
git clone https://github.com/CH6832/birthdays-management.git
```

1. Navigate into root directory:

```sh
cd birthdays-management
```

2. Install requirements:

```sh
pip3 install -r requirements.txt
```

### Run the application

```sh
py app.py
```

### Read the docs

- [HTML](/docs/build/html/index.html)
- [PDF](/docs/build/latex/birthdaysmanagement.pdf)

### Run the application in a Docker container:

0. Build the docker image

```sh
docker build -t flask-app .
```

1. Run the docker container:

```sh
docker run -d -p 5000:5000 flask-app
```

2. FLask application is now running in a docker container reachable in your webbrowser:

```sh
http://localhost:5000
```

### Build the docs

0. Move into `docs` folder

```sh
cd docs
```

1. Initilaize project:

```sh
sphinx-quickstart 
```

2. Build/rebuild the documentation:

```sh
make html
```

### Update database

0. Make changes to 'UserModel' class in `app.py`

1. Generate migration scripts in `migrations` folder:

```sh
flask db migrate -m "describe your changes"
```

2. Apply the migrations:

```sh
flask db upgrade
```

## Resources used to create this project

* Python
  * [Python 3.12 documentation](https://docs.python.org/3/)
  * [Built-in Functions](https://docs.python.org/3/library/functions.html)
  * [Python Module Index](https://docs.python.org/3/py-modindex.html)
  * [PEP 8 – Style Guide for Python Code](https://peps.python.org/pep-0008/)
* Flask
  * [Flask](https://flask.palletsprojects.com/en/3.0.x/)
* HTML
  * [HTML: HyperText Markup Language](https://developer.mozilla.org/en-US/docs/Web/HTML)
* CSS
  * [CSS: Cascading Style Sheets - MDN Web Docs](https://developer.mozilla.org/en-US/docs/Web/CSS?retiredLocale=de)
* Docker
  * [Docker Docs](https://docs.docker.com/)
* Markdwon
  * [Basic syntax](https://www.markdownguide.org/basic-syntax/)
  * [Complete list of github markdown emofis](https://dev.to/nikolab/complete-list-of-github-markdown-emoji-markup-5aia)
  * [Awesome template](http://github.com/Human-Activity-Recognition/blob/main/README.md)
  * [.gitignore file](https://git-scm.com/docs/gitignore)
* Editor
  * [Visual Studio Code](https://code.visualstudio.com/)
