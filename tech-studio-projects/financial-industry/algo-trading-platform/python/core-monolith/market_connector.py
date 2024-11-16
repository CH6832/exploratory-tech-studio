import random
import time

class MarketConnector:
    """
    Simulates a connection to a market data provider and provides access to real-time market data.

    Attributes:
        is_connected (bool): Tracks the current connection status to the market.
        symbol (str): The market symbol for which data is being fetched.
    """

    def __init__(self):
        """
        Initializes the MarketConnector with the initial connection status set to False.
        """
        self.is_connected = False
        self.symbol = None
        self.reconnect_attempts = 0

    def connect(self):
        """
        Simulate the connection to a market data provider.
        """
        print("Connecting to market data provider...")
        time.sleep(2)  # Simulating time delay for establishing a connection

        # Randomly simulate successful or failed connection
        if random.choice([True, False]):
            self.is_connected = True
            print("Connection to market data provider established.")
        else:
            self.is_connected = False
            print("Failed to connect to market data provider. Retrying...")
            self.reconnect()

    def reconnect(self):
        """
        Simulate attempting to reconnect to the market data provider.
        """
        max_retries = 3
        while self.reconnect_attempts < max_retries and not self.is_connected:
            print(f"Reconnecting attempt {self.reconnect_attempts + 1}...")
            time.sleep(2)  # Simulate a time delay for reconnection attempts
            self.reconnect_attempts += 1

            if random.choice([True, False]):  # Randomly simulate success or failure
                self.is_connected = True
                print("Reconnection successful!")
            else:
                print("Reconnection failed. Retrying...")

        if not self.is_connected:
            print(f"Unable to establish a connection after {max_retries} attempts.")

    def disconnect(self):
        """
        Simulate disconnecting from the market data provider.
        """
        if self.is_connected:
            print("Disconnecting from market data provider...")
            time.sleep(1)  # Simulating time delay for disconnection
            self.is_connected = False
            print("Disconnected from market data provider.")
        else:
            print("Already disconnected.")

    def fetch_market_data(self, symbol):
        """
        Fetch the latest market data for a given symbol.

        Args:
            symbol (str): The trading symbol for which market data is requested (e.g., "AAPL", "BTC-USD").

        Returns:
            dict: A dictionary containing the market data for the symbol (open, high, low, close, volume).
        """
        if not self.is_connected:
            print("Error: Not connected to the market data provider.")
            return None

        print(f"Fetching market data for {symbol}...")
        time.sleep(1)  # Simulate a time delay for fetching data

        # Simulate market data (open, high, low, close, volume)
        market_data = {
            "symbol": symbol,
            "timestamp": time.strftime('%Y-%m-%d %H:%M:%S', time.gmtime()),
            "open": round(random.uniform(100, 150), 2),
            "high": round(random.uniform(150, 200), 2),
            "low": round(random.uniform(90, 100), 2),
            "close": round(random.uniform(100, 150), 2),
            "volume": random.randint(1000, 10000)
        }

        print(f"Market data for {symbol}: {market_data}")
        return market_data

    def get_connection_status(self):
        """
        Returns the current connection status to the market provider.

        Returns:
            bool: True if connected, False otherwise.
        """
        return self.is_connected
