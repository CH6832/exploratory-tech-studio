from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/notify/', methods=['POST'])
def send_notification():
    notification_data = request.json
    # Mock sending a notification
    if notification_data.get('user_id') and notification_data.get('message'):
        return jsonify({'message': 'Notification sent successfully'}), 200
    return jsonify({'error': 'Invalid notification details'}), 400

if __name__ == '__main__':
    app.run(debug=True, port=5009)
