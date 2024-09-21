import json
from django.http import JsonResponse
from django.shortcuts import render
from city_trips_recommendation.scripts.city_info import get_city_info, scrape_tourist_info


def save_urls(request):
    if request.method == 'POST':
        urls = json.loads(request.POST.get('urls'))
        with open('data/urls.json', 'w') as file:
            json.dump(urls, file)
        return JsonResponse({'success': True})
    return JsonResponse({'success': False})


def cityinfo(request, city_name):
    city_info = get_city_info(city_name.replace("-","_"))
    tourist_info = scrape_tourist_info(city_name.replace("-","_"))
    return render(request, 'cityinfo.html', {'city_info': city_info, 'tourist_info': tourist_info})


def index(request):
    return render(request, 'index.html')
