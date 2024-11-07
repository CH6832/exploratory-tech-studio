import json
from flask import Flask, request, jsonify
import os

app = Flask(__name__)

# Define the path to the JSON file
ORDERS_FILE = '../data/orders.json'

# Helper function to load orders from the JSON file
def load_orders():
    if not os.path.exists(ORDERS_FILE):
        return {}
    with open(ORDERS_FILE, 'r') as file:
        return json.load(file)

# Helper function to save orders to the JSON file
def save_orders(orders):
    with open(ORDERS_FILE, 'w') as file:
        json.dump(orders, file, indent=4)

# Load orders into memory at the start
orders = load_orders()

@app.route('/orders/', methods=['POST'])
def create_order():
    order = request.json
    order_id = len(orders) + 1
    orders[order_id] = order
    # Here you'd interact with a payment service.
    save_orders(orders)  # Save to JSON file
    return jsonify({'order_id': order_id, 'message': 'Order created and payment processed'}), 201

@app.route('/orders/<int:order_id>', methods=['GET'])
def get_order(order_id):
    if order_id in orders:
        return jsonify(orders[order_id]), 200
    return jsonify({'error': 'Order not found'}), 404

@app.route('/orders/<int:order_id>/cancel', methods=['POST'])
def cancel_order(order_id):
    if order_id in orders:
        # Here you'd interact with payment service to refund.
        del orders[order_id]
        save_orders(orders)  # Save updated orders to JSON file
        return jsonify({'message': 'Order canceled and refunded'}), 200
    return jsonify({'error': 'Order not found'}), 404

if __name__ == '__main__':
    app.run(debug=True, port=5003)
