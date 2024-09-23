@echo off
cd app

start python order_service.py
start python auth_service.py
start python review_service.py
start python inventory_service.py
start python wishlist_service.py
start python payment_service.py
start python notification_service.py
start python search_service.py

cd ..
