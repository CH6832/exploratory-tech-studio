from flask import Flask, request, jsonify
import json
import os

app = Flask(__name__)

USER_PROFILES_FILE = 'data/user_profiles.json'

def load_user_profiles():
    if not os.path.exists(USER_PROFILES_FILE):
        return {}
    with open(USER_PROFILES_FILE, 'r') as file:
        return json.load(file)

def save_user_profiles(profiles):
    with open(USER_PROFILES_FILE, 'w') as file:
        json.dump(profiles, file, indent=4)

user_profiles = load_user_profiles()

@app.route('/users/<int:user_id>/', methods=['GET'])
def get_user_profile(user_id):
    return jsonify(user_profiles.get(user_id, {})), 200

@app.route('/users/', methods=['POST'])
def create_user_profile():
    profile_data = request.json
    user_id = len(user_profiles) + 1
    user_profiles[user_id] = profile_data
    save_user_profiles(user_profiles)
    return jsonify({'user_id': user_id, 'message': 'Profile created'}), 201

if __name__ == '__main__':
    app.run(debug=True, port=5011)
