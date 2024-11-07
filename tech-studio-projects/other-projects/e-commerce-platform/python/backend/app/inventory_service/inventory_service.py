import json
from flask import Flask, request, jsonify
import os

app = Flask(__name__)

# Define the path to the JSON file
INVENTORY_FILE = 'data/inventory.json'

# Helper function to load inventory from the JSON file
def load_inventory():
    if not os.path.exists(INVENTORY_FILE):
        return {}
    with open(INVENTORY_FILE, 'r') as file:
        return json.load(file)

# Helper function to save inventory to the JSON file
def save_inventory(inventory):
    with open(INVENTORY_FILE, 'w') as file:
        json.dump(inventory, file, indent=4)

# Load inventory into memory at the start
inventory = load_inventory()

@app.route('/inventory/', methods=['GET'])
def get_inventory():
    return jsonify(inventory), 200

@app.route('/inventory/<int:product_id>/', methods=['PUT'])
def update_inventory(product_id):
    stock = request.json['stock']
    inventory[product_id] = stock
    save_inventory(inventory)  # Save updated inventory to JSON file
    return jsonify({'message': 'Stock updated'}), 200

if __name__ == '__main__':
    app.run(debug=True, port=5006)
