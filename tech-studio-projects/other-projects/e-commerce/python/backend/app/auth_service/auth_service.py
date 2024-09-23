import json
from flask import Flask, request, jsonify
import jwt, datetime
import os

app = Flask(__name__)
app.config['SECRET_KEY'] = 'supersecretkey'

# Define the path to the JSON file
USERS_FILE = 'data/users.json'

# Helper function to load users from the JSON file
def load_users():
    if not os.path.exists(USERS_FILE):
        return {}
    with open(USERS_FILE, 'r') as file:
        return json.load(file)

# Helper function to save users to the JSON file
def save_users(users):
    with open(USERS_FILE, 'w') as file:
        json.dump(users, file, indent=4)

# Load users into memory at the start
users = load_users()

def generate_token(user_id):
    token = jwt.encode({'user_id': user_id, 'exp': datetime.datetime.utcnow() + datetime.timedelta(hours=1)}, app.config['SECRET_KEY'])
    return token

@app.route('/auth/register/', methods=['POST'])
def register():
    user_data = request.json
    user_id = len(users) + 1
    users[user_id] = user_data
    save_users(users)  # Save to JSON file
    return jsonify({'user_id': user_id, 'message': 'User registered'}), 201

@app.route('/auth/login/', methods=['POST'])
def login():
    login_data = request.json
    for user_id, user in users.items():
        if user['email'] == login_data['email'] and user['password'] == login_data['password']:
            token = generate_token(user_id)
            return jsonify({'token': token}), 200
    return jsonify({'error': 'Invalid credentials'}), 401

@app.route('/auth/me/', methods=['GET'])
def get_profile():
    token = request.headers.get('Authorization').split()[1]
    try:
        data = jwt.decode(token, app.config['SECRET_KEY'], algorithms=['HS256'])
        user_id = data['user_id']
        return jsonify(users[user_id]), 200
    except Exception as e:
        return jsonify({'error': 'Invalid token'}), 401

if __name__ == '__main__':
    app.run(debug=True, port=5004)
