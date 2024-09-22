#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""app.py

Them main app for the birthday management.
"""

from datetime import datetime, timedelta
from collections import defaultdict
from flask import Flask, redirect, render_template, request, Response, jsonify
from flask_migrate import Migrate
from flask_sqlalchemy import SQLAlchemy


app = Flask(__name__)

# adding configurations and create SQLAlchemy instance
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///birthdays.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
birthdays_db = SQLAlchemy(app)
migrate = Migrate(app, birthdays_db)

with app.app_context():
    birthdays_db.create_all()

@app.after_request
def after_request(response):
    """Ensure responses aren't cached"""
    response.headers["Cache-Control"] = "no-cache, no-store, must-revalidate"
    response.headers["Expires"] = 0
    response.headers["Pragma"] = "no-cache"

    return response


class UserModel(birthdays_db.Model):
    """
    User model for name and birthday data.
    """
    # name = request.form["name"]
    name = birthdays_db.Column(birthdays_db.String(20), unique=False, nullable=False, primary_key=True)
    # year
    year = birthdays_db.Column(birthdays_db.Integer, nullable=False)
    # month = request.form["month"]
    month = birthdays_db.Column(birthdays_db.Integer, nullable=False)
    # day = request.form["day"]
    day = birthdays_db.Column(birthdays_db.Integer, nullable=False)

    def __repr__(self) -> str:
        """Returns a string representation of the function."""

        return f"Name : {self.name}, Year: {self.year} Month: {self.month}, Day: {self.day}"


@app.route("/",)
def index() -> str:
    """Generate updated template"""
    today = datetime.now()
    year = today.year
    month = today.month

    rows: list = UserModel.query.all()
    age_distribution = {}
    total_birthdays = len(rows)

    month_count = {i: 0 for i in range(1, 13)}
    age_distribution = {}
    days = defaultdict(list)

    for birthday in rows:
        month_count[birthday.month] += 1
        age = datetime.now().year - birthday.year
        age_distribution[age] = age_distribution.get(age, 0) + 1
        days[birthday.day].append(birthday.name)

    most_common_month = max(month_count, key=month_count.get)

    return render_template("index.html", rows=rows, 
                                        total_birthdays=total_birthdays, 
                                        month_count=month_count, 
                                        age_distribution=age_distribution,
                                        most_common_month=most_common_month,
                                        days=days, 
                                        year=year, 
                                        month=month)


@app.route('/add', methods=["POST"])
def profile() -> Response:
    """Add data inserted by user"""
    name = request.form.get("name")
    month = request.form.get("month")
    day = request.form.get("day")
    year = request.form.get("year")

    if name != '' and year is not None and month is not None and day is not None:
        user_profile = UserModel(name=name, year=year, month=month, day=day)
        birthdays_db.session.add(user_profile)
        birthdays_db.session.commit()

        return redirect('/')

    else:

        return redirect('/')


@app.route('/delete/<string:name>', methods=["POST"])
def delete_birthday(name) -> Response:
    """Delete birthday entry"""
    user_profile = UserModel.query.get(name)
    if user_profile:
        birthdays_db.session.delete(user_profile)
        birthdays_db.session.commit()

    return redirect('/')


@app.route('/custom_calendar/<int:year>/<int:month>')
@app.route('/custom_calendar')
def custom_calendar(year=None, month=None):
    now = datetime.now()

    if year is None or month is None:
        year = now.year
        month = now.month

    # Get all birthdays
    birthdays = UserModel.query.all()

    # Create a dictionary to hold the days of the month and the birthdays
    days_in_month = {day: [] for day in range(1, 32)}

    for birthday in birthdays:
        if birthday.month == month:
            days_in_month[birthday.day].append(birthday.name)

    return render_template('custom_calendar.html', year=year, month=month, days=days_in_month)


@app.route('/statistics')
def statistics():
    birthdays = UserModel.query.all()
    total_birthdays = len(birthdays)
    
    month_count = {i: 0 for i in range(1, 13)}
    age_distribution = {}

    for birthday in birthdays:
        month_count[birthday.month] += 1
        age = datetime.now().year - birthday.year
        age_distribution[age] = age_distribution.get(age, 0) + 1

    most_common_month = max(month_count, key=month_count.get)
    
    return render_template('statistics.html', 
                           total_birthdays=total_birthdays, 
                           month_count=month_count, 
                           age_distribution=age_distribution,
                           most_common_month=most_common_month)
