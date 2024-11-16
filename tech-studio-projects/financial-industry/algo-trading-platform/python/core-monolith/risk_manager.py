class RiskManager:
    """Risk Manager class."""

    def __init__(self, max_exposure=10000):
        self.max_exposure = max_exposure

    def check_risk(self, symbol, quantity):
        """Check if the trade violates risk management rules."""
        # In a real system, this would check against account balance, exposure, etc.
        if quantity * 150.25 > self.max_exposure:
            return False
        return True
    
    def finalize_risk_report(self):
        """Provide risk report as a string here."""

        return None
