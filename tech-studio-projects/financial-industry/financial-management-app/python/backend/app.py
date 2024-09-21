from flask import Flask, jsonify, request

app = Flask(__name__)

@app.route('/api/calculate', methods=['POST'])
def calculate():
    data = request.json
    revenue = data.get('revenue')
    expenses = data.get('expenses')
    
    if revenue is None or expenses is None:
        return jsonify({'error': 'Invalid input'}), 400
    
    # Calculate net income
    net_income = revenue - expenses
    
    return jsonify({'net_income': net_income})

if __name__ == '__main__':
    app.run(debug=True)
