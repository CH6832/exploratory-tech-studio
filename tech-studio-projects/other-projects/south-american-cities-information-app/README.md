# City Explorer

South America City Explorer is a web application built with Django that provides information and images for various cities in South America. It offers features such as displaying city details, weather information, tourist attractions, and images related to each city.

## Table of Contents
- [Features](#features)
- [Getting started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Usage](#usage)
- [External APIs](#external-apis)


## Features

* Display detailed information for each city:
    * Official name, population, country, timezone, official languages, etc.   
* Show important tourist information for each city.
* Present tourist attractions and points of interest in each city.
* A map that displays the climate for the entire continent.

## Getting Started

### Prerequisites

1. Clone the repository:

```sh
git clone https://github.com/your_username/south-america-city-explorer.git
```

Install dependencies:

```sh
pip install -r requirements.txt
```

Run migrations:

```sh
py manage.py migrate
```

Start the development server:

```sh
py manage.py runserver
```

### Usage

Navigate to the homepage to explore the list of cities. Click on a city to view detailed information, weather data, tourist attractions, and images.

## External APIs

The application uses the following external API:

* [OpenWeatherMap API](https://www.openstreetmap.org) for city data.

## Other resources used to create this project

* Python
  * [Python 3.12 documentation](https://docs.python.org/3/)
  * [Built-in Functions](https://docs.python.org/3/library/functions.html)
  * [Python Module Index](https://docs.python.org/3/py-modindex.html)
  * [PEP 8 – Style Guide for Python Code](https://peps.python.org/pep-0008/)
* Flask
  * [Django documentation](https://docs.djangoproject.com/en/5.1/)
* HTML
  * [HTML: HyperText Markup Language](https://developer.mozilla.org/en-US/docs/Web/HTML)
* CSS
  * [CSS: Cascading Style Sheets - MDN Web Docs](https://developer.mozilla.org/en-US/docs/Web/CSS?retiredLocale=de)
* Markdwon
  * [Basic syntax](https://www.markdownguide.org/basic-syntax/)
  * [Complete list of github markdown emofis](https://dev.to/nikolab/complete-list-of-github-markdown-emoji-markup-5aia)
  * [Awesome template](http://github.com/Human-Activity-Recognition/blob/main/README.md)
  * [.gitignore file](https://git-scm.com/docs/gitignore)
* Editor
  * [Visual Studio Code](https://code.visualstudio.com/)
