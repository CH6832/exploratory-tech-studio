import json
from flask import Flask, request, jsonify
import os

app = Flask(__name__)

# Define the path to the JSON file
CART_FILE = 'data/shoppingcart.json'

# Helper function to load the cart from the JSON file
def load_cart():
    if not os.path.exists(CART_FILE):
        return {}
    with open(CART_FILE, 'r') as file:
        return json.load(file)

# Helper function to save the cart to the JSON file
def save_cart(cart):
    with open(CART_FILE, 'w') as file:
        json.dump(cart, file, indent=4)

# Load cart into memory at the start
cart = load_cart()

@app.route('/cart/', methods=['POST'])
def add_to_cart():
    item = request.json
    item_id = len(cart) + 1
    cart[item_id] = item
    save_cart(cart)  # Save to JSON file
    return jsonify({'item_id': item_id, 'message': 'Item added to cart'}), 201

@app.route('/cart/', methods=['GET'])
def get_cart():
    return jsonify(cart), 200

@app.route('/cart/<int:item_id>', methods=['DELETE'])
def remove_from_cart(item_id):
    if item_id in cart:
        del cart[item_id]
        save_cart(cart)  # Save updated cart to JSON file
        return jsonify({'message': 'Item removed from cart'}), 200
    return jsonify({'error': 'Item not found in cart'}), 404

if __name__ == '__main__':
    app.run(debug=True, port=5002)
