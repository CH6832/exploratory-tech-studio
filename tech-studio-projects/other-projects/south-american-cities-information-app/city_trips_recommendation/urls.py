from django.urls import path

from . import views

urlpatterns = [
    path('city/<str:city_name>/', views.cityinfo, name='cityinfo'),
    path('save_urls/', views.save_urls, name='save_urls'),
    path('provacy_policy/', views.privacy_policy, name='privacy_policy'),
    path('terms_of_service/', views.terms_of_service, name='terms_of_service'),
    path('legal_notice/', views.legal_notice, name='legal_notice'),
    path('', views.index, name='index'),

]