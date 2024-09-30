from flask import Flask
from flask_cors import CORS
from config.config import DevelopmentConfig
import logging
import logging.config

def create_app():
    app = Flask(__name__)
    app.config.from_object(DevelopmentConfig)
    
    CORS(app)  # Enable CORS for all routes

    logging.config.fileConfig('config/logging.conf')

    from api.views import main as main_blueprint
    app.register_blueprint(main_blueprint)

    return app

app = create_app()
