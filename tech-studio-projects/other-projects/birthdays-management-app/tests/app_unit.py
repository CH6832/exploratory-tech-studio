#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""app_unit.py"""

import os
import sys
import unittest
project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(project_root)
from ..app import app, birthdays_db, UserModel


class TestApp(unittest.TestCase):
    """
    Class handles unittests for a birthday application.
    """

    def setUp(self) -> None:
        """Set up the test environment"""
        app.config['TESTING'] = True
        app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///:memory:'
        self.app = app.test_client()
        with app.app_context():
            birthdays_db.create_all()

        return None


    def tearDown(self):
        """Tear down the test environment"""
        with app.app_context():
            birthdays_db.session.remove()
            birthdays_db.drop_all()

    def test_index_route(self):
        """Test the index route"""
        response = self.app.get('/')
        self.assertEqual(response.status_code, 200)
        self.assertIn(b"Welcome to the Birthday Tracker Application", response.data)

    def test_add_birthday(self):
        """Test adding a birthday entry"""
        response = self.app.post('/add', data={'name': 'John', 'year': 1990, 'month': 5, 'day': 10})
        self.assertEqual(response.status_code, 302)  # Check if redirected after adding
        with app.app_context():
            user = UserModel.query.filter_by(name='John').first()
            self.assertIsNotNone(user)  # Check if the user exists in the database

    def test_delete_birthday(self):
        """Test deleting a birthday entry"""
        with app.app_context():
            # Add a user to the database
            user = UserModel(name='Alice', year=1985, month=3, day=15)
            birthdays_db.session.add(user)
            birthdays_db.session.commit()

        # Attempt to delete the user
        response = self.app.post('/delete/Alice')
        self.assertEqual(response.status_code, 302)  # Check if redirected after deleting
        with app.app_context():
            user = UserModel.query.filter_by(name='Alice').first()
            self.assertIsNone(user)  # Check if the user no longer exists in the database


if __name__ == '__main__':
    unittest.main()
