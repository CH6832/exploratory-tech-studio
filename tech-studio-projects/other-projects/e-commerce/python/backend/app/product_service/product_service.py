import json
from flask import Flask, request, jsonify
import os

app = Flask(__name__)

# Define the path to the JSON file
PRODUCTS_FILE = 'products.json'

# Helper function to load products from the JSON file
def load_products():
    if not os.path.exists(PRODUCTS_FILE):
        return {}
    with open(PRODUCTS_FILE, 'r') as file:
        return json.load(file)

# Helper function to save products to the JSON file
def save_products(products):
    with open(PRODUCTS_FILE, 'w') as file:
        json.dump(products, file, indent=4)

# Load products into memory at the start
products = load_products()

@app.route('/products/', methods=['POST'])
def add_product():
    product_data = request.json
    product_id = len(products) + 1
    products[product_id] = product_data
    save_products(products)  # Save to JSON
    return jsonify({'id': product_id, 'message': 'Product added'}), 201

@app.route('/products/', methods=['GET'])
def get_products():
    """Get all products."""
    return jsonify(products), 200

@app.route('/products/<int:id>', methods=['PUT'])
def update_product(id):
    if id in products:
        products[id].update(request.json)
        save_products(products)  # Save updated data to JSON
        return jsonify({'message': 'Product updated'}), 200
    return jsonify({'error': 'Product not found'}), 404

@app.route('/products/<int:id>', methods=['DELETE'])
def delete_product(id):
    if id in products:
        del products[id]
        save_products(products)  # Save updated data to JSON
        return jsonify({'message': 'Product deleted'}), 200
    return jsonify({'error': 'Product not found'}), 404

if __name__ == '__main__':
    app.run(debug=True, port=5001)
