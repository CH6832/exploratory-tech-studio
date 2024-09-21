from django.urls import path

from . import views

urlpatterns = [
    path('city/<str:city_name>/', views.cityinfo, name='cityinfo'),
    path('data/', views.save_urls, name='save_urls'),
    path('', views.index, name='index')
]