from flask import Flask, request, jsonify

app = Flask(__name__)

coupons = {}

@app.route('/coupons/', methods=['POST'])
def create_coupon():
    coupon_data = request.json
    coupon_code = coupon_data['code']
    coupons[coupon_code] = coupon_data
    return jsonify({'message': 'Coupon created'}), 201

@app.route('/coupons/<string:code>/', methods=['GET'])
def get_coupon(code):
    return jsonify(coupons.get(code, {})), 200

if __name__ == '__main__':
    app.run(debug=True, port=5014)
