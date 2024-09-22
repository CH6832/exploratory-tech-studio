#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""views.py

Logic handling for the web application.
"""

import json
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render
from city_trips_recommendation.scripts.city_info import get_city_info, scrape_tourist_info, fetch_and_predict_weather, calculate_optimal_route


def privacy_policy(request):
    """Render the privacy policy page."""
    
    return render(request, 'legal/privacy_policy.html')


def terms_of_service(request):
    """Render the terms of service page."""

    return render(request, 'legal/terms_of_service.html')


def legal_notice(request):
    """Render the terms of service page."""

    return render(request, 'legal/legal_notice.html')


def save_urls(request):
    """Save all urls."""
    if request.method == 'POST':
        urls = json.loads(request.POST.get('urls'))
        with open('data/urls.json', 'w', encoding="utf-8") as file:
            json.dump(urls, file)

        return JsonResponse({'success': True})

    return JsonResponse({'success': False})


def cityinfo(request, city_name) -> HttpResponse:
    """Render all information about a single city."""
    city_info = get_city_info(city_name.replace("-","_"))
    tourist_info = scrape_tourist_info(city_name.replace("-","_"))
    weather_info = fetch_and_predict_weather(city_name)
    # recommend_optimal_travel_time = recommend_optimal_travel_time(city_name)
    optimal_route_info = calculate_optimal_route(city_name)


    return render(request, 'cityinfo.html', {'city_info': city_info, 'tourist_info': tourist_info, 'weather_info': weather_info, 'optimal_route_info': optimal_route_info})


def index(request) -> HttpResponse:
    """Render the index page of the web application."""
    
    return render(request, 'index.html')
