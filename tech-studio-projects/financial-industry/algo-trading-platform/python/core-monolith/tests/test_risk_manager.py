# tests/python/yourcompany/risk/test_risk_manager.py

import pytest
from yourcompany.risk.risk_manager import RiskManager

@pytest.fixture
def risk_manager():
    """Fixture for RiskManager instance."""
    return RiskManager(max_exposure=10000)

def test_check_risk_within_limit(risk_manager):
    """
    Test that check_risk returns True when the trade quantity is within the risk limit.
    """
    result = risk_manager.check_risk("AAPL", 50)  # 50 * 150.25 = 7512.5
    assert result is True

def test_check_risk_exceeds_limit(risk_manager):
    """
    Test that check_risk returns False when the trade quantity exceeds the risk limit.
    """
    result = risk_manager.check_risk("AAPL", 100)  # 100 * 150.25 = 15025.0
    assert result is False
