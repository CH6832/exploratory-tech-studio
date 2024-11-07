import json
from flask import Flask, request, jsonify
import os

app = Flask(__name__)

# Define the path to the JSON file
PRODUCTS_FILE = 'data/products.json'

# Helper function to load products from the JSON file
def load_products():
    if not os.path.exists(PRODUCTS_FILE):
        return {}
    with open(PRODUCTS_FILE, 'r') as file:
        return json.load(file)

# Load products into memory at the start
products = load_products()

@app.route('/search/', methods=['GET'])
def search_products():
    query = request.args.get('q', '').lower()
    results = {k: v for k, v in products.items() if query in v['name'].lower()}
    return jsonify(results), 200

if __name__ == '__main__':
    app.run(debug=True, port=5010)
