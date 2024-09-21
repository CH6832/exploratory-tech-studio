
from flask import request, jsonify
from app import app, db, logger
from models import BlackScholesInputs
from black_scholes import black_scholes_call, black_scholes_put

@app.route('/calculate', methods=['POST'])
def calculate():
    try:
        data = request.get_json()
        stock_price = data['stock_price']
        strike_price = data['strike_price']
        time_to_maturity = data['time_to_maturity']
        volatility = data['volatility']
        interest_rate = data['interest_rate']

        call_price = black_scholes_call(stock_price, strike_price, time_to_maturity, volatility, interest_rate)
        put_price = black_scholes_put(stock_price, strike_price, time_to_maturity, volatility, interest_rate)

        # Save to database
        input_record = BlackScholesInputs(
            StockPrice=stock_price,
            StrikePrice=strike_price,
            TimeToExpiry=time_to_maturity,
            Volatility=volatility,
            InterestRate=interest_rate
        )
        db.session.add(input_record)
        db.session.commit()

        return jsonify({
            'call_price': call_price,
            'put_price': put_price
        })

    except Exception as e:
        logger.error(f"Error calculating option prices: {e}")
        return jsonify({'error': 'Internal server error'}), 500

