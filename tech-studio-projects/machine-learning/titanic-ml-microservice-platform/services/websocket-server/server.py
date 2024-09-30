from flask import Flask
from flask_socketio import SocketIO

app = Flask(__name__)
socketio = SocketIO(app)

@app.route('/')
def index():
    return "WebSocket Server Running!"

@socketio.on('message')
def handle_message(msg):
    print('Received message: ' + msg)

if __name__ == '__main__':
    socketio.run(app, host='0.0.0.0', port=8080)
