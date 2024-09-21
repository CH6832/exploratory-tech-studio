.. Birthdays Management documentation master file, created by
   sphinx-quickstart on Thu May  9 08:55:43 2024.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to Birthdays Management's documentation!
================================================

.. toctree::
   :maxdepth: 2
   :caption: Contents:

Indices and tables
==================

* :ref:`genindex`
* :ref:`modindex`
* :ref:`search`

Welcome to the documentation for the Birthday Tracker Application! This documentation provides comprehensive information about the functionality, architecture, installation, and usage of the Birthday Tracker Application. Whether you're a developer looking to understand the technical aspects of the application or a user seeking guidance on how to use its features, you'll find everything you need here.

Table of Contents
-----------------
1. User Documentation
2. Technical Documentation

.. _user-documentation:

User Documentation
==================

Adding Birthdays
----------------

To add a birthday entry:

1. Navigate to the homepage of the Birthday Tracker Application.
2. Enter your name, birth month, and birth day into the provided input fields.
3. Click the "Add" button to submit the form.

Viewing Birthdays
------------------

To view the list of birthdays:

1. Navigate to the homepage of the Birthday Tracker Application.
2. The application will display a list of all birthday entries, including names, birth months, and birth days.

Deleting Birthdays
------------------

To delete a birthday entry:

1. Navigate to the homepage of the Birthday Tracker Application.
2. Find the birthday entry you want to delete in the list.
3. Click the "Delete" button next to the entry to remove it from the database.

Technology Stack
----------------

The Birthday Tracker Application is built using the following technologies:

- Flask: A micro web framework for Python used for building the web application.
- Flask-SQLAlchemy: A Flask extension that adds SQLAlchemy support to the application, allowing interaction with a SQL database.
- Flask-Migrate: A Flask extension for database migrations, used for managing changes to the database schema.

Database Schema
---------------

The application uses a SQLite database to store birthday entries. The database schema consists of a single table named UserModel with the following columns:

- name: The name of the user (primary key).
- month: The birth month of the user.
- day: The birth day of the user.

Contributors
------------

The Birthday Tracker Application was created by [Your Name] and is maintained by Christoph Hartleb.

.. _technical-documentation:

Technical Documentation
=======================

Overview
--------

The Birthday Tracker Application is a Flask-based web application that allows users to add, view, and delete birthday entries. It leverages Flask for routing and request handling, Flask-SQLAlchemy for database interaction, and Flask-Migrate for database migrations.

Architecture
------------

The application follows a typical MVC (Model-View-Controller) architecture:

- Model: Defined in the UserModel class, representing the structure of the database table for storing birthday entries.
- View: Implemented using HTML templates (index.html) rendered by Flask's render_template function, displaying the user interface for adding, viewing, and deleting birthday entries.
- Controller: Implemented in the Flask application (app.py), handling user requests, processing data, and interacting with the database.

Database Schema
---------------

The application uses a SQLite database to store birthday entries. The database schema consists of a single table named UserModel with the following columns:

- name: The name of the user (primary key).
- month: The birth month of the user.
- day: The birth day of the user.

Routes and Controllers
-----------------------

The Flask application defines the following routes and controllers:

- GET /: Renders the homepage with a list of all birthday entries.
  Controller: index function in app.py.
- POST /add: Adds a new birthday entry to the database.
  Controller: profile function in app.py.
- POST /delete/:name: Deletes a birthday entry from the database.
  Controller: delete_birthday function in app.py.

Dependencies
------------

The application relies on the following dependencies:

- Flask: A micro web framework for Python, used for routing and request handling.
- Flask-SQLAlchemy: Flask extension for SQLAlchemy integration, facilitating database interaction.
- Flask-Migrate: Flask extension for database migrations, enabling schema changes and updates.

Installation and Setup
----------------------

To install and run the Birthday Tracker Application:

1. Clone the repository from <repository-url>.
2. Install dependencies using pip:

   .. code-block:: bash

      pip install Flask Flask-SQLAlchemy Flask-Migrate

3. Run the Flask application:

   .. code-block:: bash

      python app.py

4. Access the application at http://localhost:5000 in a web browser.

Future Improvements
--------------------

Potential future improvements for the application include:

- Adding authentication and user management functionality.
- Implementing frontend enhancements using JavaScript frameworks like Vue.js or React.
- Enhancing error handling and validation for user inputs.
- Supporting additional features such as email reminders for upcoming birthdays.
