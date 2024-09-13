#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""city_info.py

Modules handles information mgmgt about all cities.
"""

import pprint
from typing import Any
from bs4 import BeautifulSoup
import requests


def get_city_info(city_name: str) -> (dict[str, Any] | None):
    """Retrieves information about a city based on its name by querying the REST Countries API."""
    url = f"https://restcountries.com/v3.1/capital/{city_name}"
    try:
        response = requests.get(url)
        if response.status_code == 200:
            data = response.json()
            pprint.pprint(data)
            if data:
                country_data = data[0] # Assuming the first result is the correct one
                city_info = {
                    'name': country_data.get('capital')[0],
                    'native_name': country_data.get('name').get('official'),
                    'population': country_data.get('population'),
                    'continent': country_data.get('continents'), 
                    'country': country_data.get('name').get('common'),
                    'timezone': country_data.get('timezones')[0], # Assuming only one timezone for simplicity
                    'language' : country_data.get('languages'),
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
        response = requests.get(url)
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


