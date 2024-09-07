# City Explorer

South America City Explorer is a web application built with Django that provides information and images for various cities in South America. It offers features such as displaying city details, weather information, tourist attractions, and images related to each city.

## Features

* Display detailed information for each city, including population, area, and more.
* Show current weather information for each city.
* Present tourist attractions and points of interest in each city.
* Showcase high-quality images of each city obtained from the Unsplash API.

## Getting Started

### Prerequisites

- [Python 3.x](https://www.python.org/downloads/)
- [Django](https://www.djangoproject.com/)

### Installation

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
python manage.py migrate
```

Start the development server:

```sh
python manage.py runserver
```

## :pencil2: Getting Started

### Usage

Navigate to the homepage to explore the list of cities. Click on a city to view detailed information, weather data, tourist attractions, and images.

### External APIs

The application uses the following external APIs:

* [OpenWeatherMap API](https://openweathermap.org/api) for weather data.
* [OpenTripMap API](https://dev.opentripmap.org/product) for tourist information.
* [Unsplash API](https://help.unsplash.com/en/articles/2511245-unsplash-api-guidelines) for city images.

## :bookmark: License

This project is licensed under the terms of the [MIT License](LICENSE).

## :copyright: Copyright

See the [COPYRIGHT](COPYRIGHT) file for copyright and licensing details.
