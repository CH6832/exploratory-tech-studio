import yfinance as yf
import json
from datetime import datetime

class MarketData:
    """
    MarketData class to fetch market data from Yahoo Finance API.
    """

    def __init__(self, ticker="AAPL"):
        """
        Initializes the MarketData object with a default stock ticker (e.g., "AAPL").
        
        Args:
            ticker (str): The stock ticker symbol (e.g., "AAPL").
        """
        self.ticker = ticker

    def fetch_market_data(self, start_date="2023-01-01", end_date="2023-12-31"):
        """
        Fetches historical market data for the given ticker symbol from Yahoo Finance.
        
        Args:
            start_date (str): Start date for the data in 'YYYY-MM-DD' format.
            end_date (str): End date for the data in 'YYYY-MM-DD' format.
        
        Returns:
            dict: A dictionary containing market data.
        """
        print(f"Fetching market data for {self.ticker} from {start_date} to {end_date}...")
        
        # Use yfinance to fetch historical data
        data = yf.download(self.ticker, start=start_date, end=end_date)

        if not data.empty:
            formatted_data = {}
            for date, row in data.iterrows():
                formatted_data[date.strftime('%Y-%m-%d')] = {
                    "open": row["Open"],
                    "high": row["High"],
                    "low": row["Low"],
                    "close": row["Close"],
                    "volume": row["Volume"]
                }

            return formatted_data
        else:
            print(f"Error: No data found for symbol '{self.ticker}' in the given date range.")
            return {}

# Example usage:
if __name__ == "__main__":
    data_handler = MarketData(ticker="AAPL")
    market_data = data_handler.fetch_market_data(start_date="2023-01-01", end_date="2023-01-10")
    print(json.dumps(market_data, indent=4))
