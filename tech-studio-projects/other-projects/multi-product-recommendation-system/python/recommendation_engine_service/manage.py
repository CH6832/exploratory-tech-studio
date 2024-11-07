#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
manage.py
---------
This file is used to manage the Django project from the command line.
It runs administrative tasks like database migrations, running the server, etc.
"""

import os
import sys

def main():
    """
    Entry point for running Django management commands from the command line.
    """
    os.environ.setdefault("DJANGO_SETTINGS_MODULE", "recommendation_engine_service.settings")
    try:
        from django.core.management import execute_from_command_line
    except ImportError as exc:
        raise ImportError(
            "Could not import Django. Are you sure it's installed and "
            "available on your PYTHONPATH environment variable?"
        ) from exc
    execute_from_command_line(sys.argv)

if __name__ == "__main__":
    main()
