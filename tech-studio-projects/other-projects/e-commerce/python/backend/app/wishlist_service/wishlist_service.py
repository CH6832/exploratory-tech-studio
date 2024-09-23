import json
from flask import Flask, request, jsonify
import os

app = Flask(__name__)

# Define the path to the JSON file
WISHLIST_FILE = 'data/wishlist.json'

# Helper function to load wishlists from the JSON file
def load_wishlists():
    if not os.path.exists(WISHLIST_FILE):
        return {}
    with open(WISHLIST_FILE, 'r') as file:
        return json.load(file)

# Helper function to save wishlists to the JSON file
def save_wishlists(wishlists):
    with open(WISHLIST_FILE, 'w') as file:
        json.dump(wishlists, file, indent=4)

# Load wishlists into memory at the start
wishlists = load_wishlists()

@app.route('/wishlist/<int:user_id>/', methods=['GET'])
def get_wishlist(user_id):
    return jsonify(wishlists.get(user_id, [])), 200

@app.route('/wishlist/<int:user_id>/', methods=['POST'])
def add_to_wishlist(user_id):
    item = request.json
    if user_id not in wishlists:
        wishlists[user_id] = []
    wishlists[user_id].append(item)
    save_wishlists(wishlists)  # Save to JSON file
    return jsonify({'message': 'Item added to wishlist'}), 201

@app.route('/wishlist/<int:user_id>/<int:item_index>/', methods=['DELETE'])
def remove_from_wishlist(user_id, item_index):
    if user_id in wishlists and 0 <= item_index < len(wishlists[user_id]):
        removed_item = wishlists[user_id].pop(item_index)
        save_wishlists(wishlists)  # Save updated wishlist to JSON file
        return jsonify({'message': 'Item removed from wishlist', 'item': removed_item}), 200
    return jsonify({'error': 'Item not found in wishlist'}), 404

if __name__ == '__main__':
    app.run(debug=True, port=5007)
