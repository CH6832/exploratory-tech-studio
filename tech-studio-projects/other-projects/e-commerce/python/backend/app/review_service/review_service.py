import json
from flask import Flask, request, jsonify
import os

app = Flask(__name__)

# Define the path to the JSON file
REVIEWS_FILE = 'data/reviews.json'

# Helper function to load reviews from the JSON file
def load_reviews():
    if not os.path.exists(REVIEWS_FILE):
        return {}
    with open(REVIEWS_FILE, 'r') as file:
        return json.load(file)

# Helper function to save reviews to the JSON file
def save_reviews(reviews):
    with open(REVIEWS_FILE, 'w') as file:
        json.dump(reviews, file, indent=4)

# Load reviews into memory at the start
reviews = load_reviews()

@app.route('/products/<int:product_id>/reviews/', methods=['POST'])
def add_review(product_id):
    review = request.json
    if product_id not in reviews:
        reviews[product_id] = []
    reviews[product_id].append(review)
    save_reviews(reviews)  # Save to JSON file
    return jsonify({'message': 'Review added'}), 201

@app.route('/products/<int:product_id>/reviews/', methods=['GET'])
def get_reviews(product_id):
    return jsonify(reviews.get(product_id, [])), 200

if __name__ == '__main__':
    app.run(debug=True, port=5005)
