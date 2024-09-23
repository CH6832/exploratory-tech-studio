#!/bin/bash

# Navigate to the app directory
cd app

# Start each service in the background
python order_service.py &
python auth_service.py &
python review_service.py &
python inventory_service.py &
python wishlist_service.py &
python payment_service.py &
python notification_service.py &
python search_service.py &

# Wait for all background jobs to finish
wait
