from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/shipping/calculate/', methods=['POST'])
def calculate_shipping():
    data = request.json
    # Mock calculation
    shipping_cost = data.get('weight', 0) * 0.5  # Simple calculation
    return jsonify({'shipping_cost': shipping_cost}), 200

if __name__ == '__main__':
    app.run(debug=True, port=5012)
