from flask import Flask, request, jsonify
import json
import os
from datetime import datetime

app = Flask(__name__)

ACTIVITY_LOG_FILE = 'data/activity_log.json'

def load_activity_log():
    if not os.path.exists(ACTIVITY_LOG_FILE):
        return []
    with open(ACTIVITY_LOG_FILE, 'r') as file:
        return json.load(file)

def save_activity_log(log):
    with open(ACTIVITY_LOG_FILE, 'w') as file:
        json.dump(log, file, indent=4)

activity_log = load_activity_log()

@app.route('/activity/log/', methods=['POST'])
def log_activity():
    activity_data = request.json
    activity_data['timestamp'] = datetime.utcnow().isoformat()
    activity_log.append(activity_data)
    save_activity_log(activity_log)
    return jsonify({'message': 'Activity logged'}), 201

@app.route('/activity/log/', methods=['GET'])
def get_activity_log():
    return jsonify(activity_log), 200

if __name__ == '__main__':
    app.run(debug=True, port=5018)
