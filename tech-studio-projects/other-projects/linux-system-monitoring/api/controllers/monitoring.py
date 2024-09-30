from flask import jsonify, request
import json
import subprocess
import os

@main.route('/api/monitor', methods=['GET'])
def monitor():
    remote_host = request.args.get('host')

    if remote_host:
        try:
            command = f"ssh -o StrictHostKeyChecking=no user@{remote_host} 'cat /path/to/monitor_data.json'"
            result = subprocess.run(command, shell=True, capture_output=True, text=True)
            if result.returncode == 0:
                return jsonify(json.loads(result.stdout))
            else:
                return jsonify({"error": "Failed to fetch data from remote host"}), 500
        except Exception as e:
            return jsonify({"error": str(e)}), 500
    else:
        local_data_file = '/path/to/monitor_data.json'
        if os.path.exists(local_data_file):
            with open(local_data_file) as json_file:
                data = json.load(json_file)
            return jsonify(data)
        else:
            return jsonify({"error": "Local data file not found"}), 404
