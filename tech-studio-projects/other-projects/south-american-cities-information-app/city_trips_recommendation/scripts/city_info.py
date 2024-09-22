#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""city_info.py

Modules handles information mgmgt about all cities.
"""

import math
import pprint
from typing import Any
from bs4 import BeautifulSoup
import requests
from meteostat import Point, Daily
from datetime import datetime
from statsmodels.tsa.arima.model import ARIMA


capitals = [
    {
        "name": "Medellín",
        "location": [6.2442, -75.5812],
        "metadata_url": "/city/medellin",
    },
    {
        "name": "Buenos Aires",
        "location": [-34.6037, -58.3816],
        "metadata_url": "/city/buenos-aires/",
    },
    {
        "name": "Brasília",
        "location": [-15.8267, -47.9218],
        "metadata_url": "/city/brasilia/",
    },
    {
        "name": "Santiago",
        "location": [-33.4489, -70.6693],
        "metadata_url": "/city/santiago/",
    },
    {
        "name": "Bogotá",
        "location": [4.711, -74.0721],
        "metadata_url": "/city/bogota",
    },
    {
        "name": "Quito",
        "location": [-0.1807, -78.4678],
        "metadata_url": "/city/quito/",
    },
    {
        "name": "Georgetown",
        "location": [6.8013, -58.1551],
        "metadata_url": "/city/georgetown/",
    },
    {
        "name": "Asunción",
        "location": [-25.2637, -57.5759],
        "metadata_url": "/city/asuncion/",
    },
    {
        "name": "Lima",
        "location": [-12.0464, -77.0428],
        "metadata_url": "/city/lima/",
    },
    {
        "name": "Paramaribo",
        "location": [5.852, -55.2038],
        "metadata_url": "/city/paramaribo/",
    },
    {
        "name": "Montevideo",
        "location": [-34.9011, -56.1645],
        "metadata_url": "/city/montevideo/",
    },
    {
        "name": "Caracas",
        "location": [10.4806, -66.9036],
        "metadata_url": "/city/caracas/",
    },
    {
        "name": "Sucre",
        "location": [-19.0196, -65.2619],
        "metadata_url": "/city/sucre/",
    },
    # Add more capital cities with metadata URLs as needed
]


def format_population(population) -> str:
    """Helper function: Format Popluation string."""
    formatted_population: str = ""
    if population >= 1_000_000:
        formatted_population = "{:,} Million".format(population // 1_000_000)
    else:
        formatted_population = "{:,} Thousand".format(population // 1_000)

    return formatted_population

def get_city_info(city_name: str) -> (dict[str, Any] | None):
    """Retrieves information about a city based on its name by querying the REST Countries API."""
    url = f"https://restcountries.com/v3.1/capital/{city_name}"
    try:
        response = requests.get(url, timeout=5)
        if response.status_code == 200:
            data = response.json()
            pprint.pprint(data)
            if data:
                country_data = data[0] # Assuming the first result is the correct one
                city_info = {
                    'name': country_data.get('capital')[0],
                    'native_name': country_data.get('name').get('official'),
                    'population': format_population(country_data.get('population')),
                    'continent': country_data.get('continents')[0], 
                    'country': country_data.get('name').get('common'),
                    'timezone': country_data.get('timezones')[0], # Assuming only one timezone for simplicity
                    'language' : ", ".join(country_data.get('languages').values()),
                    'maps': country_data.get('maps').get('googleMaps')
                    # Add more fields as needed
                }

                return city_info
            
    except requests.RequestException as e:
        print("An error occurred: %s", e)
    
    return None


def scrape_tourist_info(city_name: str) -> (list | None):
    """Scrapes tourist information about a city from its Wikipedia page."""
    url = f"https://en.wikipedia.org/wiki/{city_name}"
    try:
        response = requests.get(url, timeout=5)
        if response.status_code == 200:
            soup = BeautifulSoup(response.content, 'html.parser')
            attractions = []
            # Find all paragraphs containing tourist information
            tourist_paragraphs = soup.find_all('p')
            for paragraph in tourist_paragraphs:
                # Check if the paragraph contains tourist attractions
                if "attraction" in paragraph.text.lower():
                    # Extract text from the paragraph and split it into attractions
                    attractions.extend(paragraph.text.strip().split('\n'))

            return attractions

    except requests.RequestException as e:
        print("An error occurred: %s", e)

    return None

def fetch_and_predict_weather(city_name):
    """Fetch and predict the weather for each city here."""
    # Normalize city name for lookup
    city_key = city_name.replace("-", "_").lower()
    
    # Check if the city exists in the capitals dictionary
    for entry in capitals:
        for key, property in entry.items():
            if key == "metadata_url":
                if city_key in property:
                    new_entry = entry
                    break
                break
    
    print(new_entry)
    # Get the location for the city
    lat, lon = new_entry["location"]
    location = Point(lat, lon)
    
    # Set the historical data range
    start = datetime(2010, 1, 1)  # Default start date for historical data
    end = datetime.now()          # End date is the current date
    
    # Fetch historical daily weather data (temperature, precipitation, etc.)
    data = Daily(location, start, end)
    df = data.fetch()

    if df.empty:
        print(f"No data available for {city_name}")
        return None
    
    # Extract temperature data (adjust if needed)
    df = df[['tavg']].dropna()  # 'tavg' is the average daily temperature

    # Train ARIMA model (simplest form for univariate time series)
    model = ARIMA(df['tavg'], order=(5, 1, 0))  # ARIMA parameters (5,1,0)
    model_fit = model.fit()

    # Forecast temperature for the next 7 days
    forecast_days = 7
    forecast = model_fit.forecast(steps=forecast_days)

    # Format the forecast as a string
    forecast_str = ', '.join([f"{temp:.2f}°C" for temp in forecast])
    
    # Return the forecast string
    return forecast_str


def haversine(lat1, lon1, lat2, lon2):
    """"""
    # Radius of the Earth in kilometers
    R = 6371.0
    
    # Convert latitude and longitude from degrees to radians
    lat1_rad = math.radians(lat1)
    lon1_rad = math.radians(lon1)
    lat2_rad = math.radians(lat2)
    lon2_rad = math.radians(lon2)
    
    # Haversine formula
    dlat = lat2_rad - lat1_rad
    dlon = lon2_rad - lon1_rad
    a = (math.sin(dlat / 2) ** 2 +
         math.cos(lat1_rad) * math.cos(lat2_rad) * (math.sin(dlon / 2) ** 2))
    c = 2 * math.asin(math.sqrt(a))
    
    # Distance in kilometers
    distance = R * c

    return distance


def calculate_optimal_route(city_name) -> str:
    """Calculate the great-circle distance between two points on the
    Earth (specified in decimal degrees) with the haversine algorithm."""
    # Check if the city exists in the capitals dictionary
    city_key = city_name.replace("-", "_").lower()
    for entry in capitals:
        for key, property in entry.items():
            if key == "metadata_url":
                if city_key in property:
                    new_entry = entry
                    break
                break

    # Get latitude and longitude for both cities
    lat1, lon1 = new_entry["location"]
    lat2, lon2 = [28.538336, -81.379234]

    # Convert decimal degrees to radians
    lat1, lon1, lat2, lon2 = map(math.radians, [lat1, lon1, lat2, lon2])

    # Haversine formula
    dlat = lat2 - lat1
    dlon = lon2 - lon1
    a = math.sin(dlat / 2) ** 2 + math.cos(lat1) * math.cos(lat2) * math.sin(dlon / 2) ** 2
    c = 2 * math.asin(math.sqrt(a))
    r = 6371  # Radius of Earth in kilometers

    return str(c * r)  # Return distance in kilometers


# def recommend_optimal_travel_time(city_name):
#     """"""
#     # Load your datasets
#     weather_data = pd.read_csv(f"data/{city_name}_weather.csv")  # Historical weather data
#     events_data = pd.read_csv(f"data/{city_name}_events.csv")      # Events data
#     prices_data = pd.read_csv(f"data/{city_name}_prices.csv")      # Hotel prices data

#     # Analyze the data
#     optimal_month = None
#     best_score = float('-inf')

#     for month in range(1, 13):  # From January (1) to December (12)
#         # Calculate average weather conditions, event count, and average price for the month
#         avg_temp = weather_data[weather_data['month'] == month]['avg_temp'].mean()
#         event_count = events_data[events_data['month'] == month].shape[0]
#         avg_price = prices_data[prices_data['month'] == month]['avg_price'].mean()

#         # Create a score based on your criteria
#         score = (avg_temp - 20) + (event_count * 2) - (avg_price / 100)

#         if score > best_score:
#             best_score = score
#             optimal_month = month

#     # Return recommendation
#     month_name = datetime(2023, optimal_month, 1).strftime('%B')
    
#     return f"The best time to visit {city_name} is in {month_name}."