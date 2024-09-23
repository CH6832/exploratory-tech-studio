from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/payment/', methods=['POST'])
def process_payment():
    payment_data = request.json
    # Mock payment processing
    if payment_data.get('amount') and payment_data.get('method'):
        return jsonify({'message': 'Payment processed successfully'}), 201
    return jsonify({'error': 'Invalid payment details'}), 400

if __name__ == '__main__':
    app.run(debug=True, port=5008)
