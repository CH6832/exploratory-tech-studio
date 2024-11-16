# tests/python/yourcompany/data/test_market_data.py

import pytest
from unittest.mock import patch
from yourcompany.data.market_data import MarketData

@pytest.fixture
def market_data():
    """Fixture for MarketData instance."""
    return MarketData("https://api.tradingplatform.com/api/v1")

@patch('yourcompany.data.market_data.requests.get')
def test_fetch_market_data_success(mock_get, market_data):
    """
    Test that fetch_market_data returns the correct market data when the API
    responds successfully.
    """
    # Mocking the API response
    mock_get.return_value.status_code = 200
    mock_get.return_value.json.return_value = {
        "symbol": "AAPL",
        "price": 150.25,
        "timestamp": "2024-11-13T15:00:00Z"
    }

    result = market_data.fetch_market_data("AAPL", "1m")
    
    # Assert that the data returned matches the expected response
    assert result["symbol"] == "AAPL"
    assert result["price"] == 150.25
    assert result["timestamp"] == "2024-11-13T15:00:00Z"

@patch('yourcompany.data.market_data.requests.get')
def test_fetch_market_data_failure(mock_get, market_data):
    """
    Test that fetch_market_data handles API failure responses gracefully.
    """
    # Mocking a failed API response
    mock_get.return_value.status_code = 500
    mock_get.return_value.json.return_value = {"error": "Unable to fetch market data"}

    result = market_data.fetch_market_data("AAPL", "1m")
    
    # Assert that the error message is returned when the API fails
    assert result["error"] == "Unable to fetch market data"
