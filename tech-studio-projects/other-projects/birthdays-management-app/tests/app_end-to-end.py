#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""app_end-to-end.py"""

import os
import sys
project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(project_root)
from selenium import webdriver
from selenium.webdriver.common.by import By
import time


def test_index_page() -> None:
    """Test the index page."""
    driver = webdriver.Chrome()
    driver.get('http://localhost:5000')
    # Assert page title
    assert "Birthday Tracker Application" in driver.title
    # Assert page content
    assert "Welcome to the Birthday Tracker Application" in driver.page_source
    driver.quit()

    return None


def test_add_and_delete_birthday():
    """Test adding and deleting a birthday entry."""
    driver = webdriver.Chrome()
    driver.get('http://localhost:5000')

    # Add a birthday entry
    name_input = driver.find_element(By.ID, 'name')
    name_input.send_keys('John')
    year_input = driver.find_element(By.ID, 'year')
    year_input.send_keys('1990')
    month_input = driver.find_element(By.ID, 'month')
    month_input.send_keys('5')
    day_input = driver.find_element(By.ID, 'day')
    day_input.send_keys('10')
    add_button = driver.find_element(By.CLASS_NAME, 'add-btn')
    add_button.click()

    # Wait for redirect
    time.sleep(1)

    # Assert user is added
    assert 'John' in driver.page_source

    # Delete the added user
    delete_button = driver.find_element(By.XPATH, '//button[contains(text(), "Delete")]')
    delete_button.click()

    # Wait for redirect
    time.sleep(1)

    # Assert user is deleted
    assert 'John' not in driver.page_source

    driver.quit()

    return None


if __name__ == '__main__':
    test_index_page()
    test_add_and_delete_birthday()
