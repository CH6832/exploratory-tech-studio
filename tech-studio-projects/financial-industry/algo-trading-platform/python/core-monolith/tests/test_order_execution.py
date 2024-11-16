# tests/python/yourcompany/execution/test_order_execution.py

import pytest
from unittest.mock import patch
from yourcompany.execution.order_execution import ExecutionService

@pytest.fixture
def execution_service():
    """Fixture for ExecutionService instance."""
    return ExecutionService("https://api.tradingplatform.com/api/v1")

@patch('yourcompany.execution.order_execution.requests.post')
def test_place_order_success(mock_post, execution_service):
    """
    Test that place_order successfully submits an order to the API.
    """
    # Mocking the API response for a successful order
    mock_post.return_value.status_code = 200
    mock_post.return_value.json.return_value = {
        "status": "success",
        "tradeID": "123456",
        "executionTime": "2024-11-13T15:00:00Z",
        "message": "Order placed successfully"
    }

    result = execution_service.place_order("AAPL", 100, 150.25, "limit")
    
    # Assert that the order response is as expected
    assert result["status"] == "success"
    assert result["tradeID"] == "123456"
    assert result["message"] == "Order placed successfully"

@patch('yourcompany.execution.order_execution.requests.post')
def test_place_order_failure(mock_post, execution_service):
    """
    Test that place_order handles failure responses from the API.
    """
    # Mocking a failed order placement response
    mock_post.return_value.status_code = 400
    mock_post.return_value.json.return_value = {"error": "Bad Request"}

    result = execution_service.place_order("AAPL", 100, 150.25, "limit")
    
    # Assert that an error message is returned when the API fails
    assert result["error"] == "Bad Request"
