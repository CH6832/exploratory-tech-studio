from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from .config import Config

app = Flask(__name__)
app.config.from_object(Config)
db = SQLAlchemy(app)

def create_db():
    db.create_all()

if __name__ == '__main__':
    create_db()

5. config.py: Configuration

python

import os

class Config:
    SQLALCHEMY_DATABASE_URI = os.getenv('DATABASE_URL', 'mysql://user:password@localhost/tomer_dev_db')
    SQLALCHEMY_TRACK_MODIFICATIONS = False