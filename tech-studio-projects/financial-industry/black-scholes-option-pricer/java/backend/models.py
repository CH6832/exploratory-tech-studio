import os
import sys
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from app import db


class BlackScholesInputs(db.Model):
    __tablename__ = 'BlackScholesInputs'

    CalculationId = db.Column(db.Integer, primary_key=True, autoincrement=True)
    StockPrice = db.Column(db.Numeric(18, 9), nullable=False)
    StrikePrice = db.Column(db.Numeric(18, 9), nullable=False)
    InterestRate = db.Column(db.Numeric(18, 9), nullable=False)
    Volatility = db.Column(db.Numeric(18, 9), nullable=False)
    TimeToExpiry = db.Column(db.Numeric(18, 9), nullable=False)