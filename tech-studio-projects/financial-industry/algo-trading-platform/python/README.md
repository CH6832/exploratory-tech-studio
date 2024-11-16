# Algorithmic Trading System

## Overview

This project simulates a fully-functional core monolith trading system, designed to handle the following tasks:
- **Market Data Handling**: Fetch historical market data for stock symbols.
- **Trading Engine**: Simulate trading strategies based on historical and live market data.
- **Order Execution**: Place and manage orders with simulated buy/sell logic.
- **Risk Management**: Evaluate trading risks and ensure safe execution.
- **Logging**: Track key system activities for analysis.

The system consists of several components that interact to process market data, execute trades, and manage risk. The entire workflow is simulated in Python, with the ability to fetch real market data, simulate trading strategies, and execute orders.

## Features
- **Data Handling**: Fetch historical stock data via free APIs like Yahoo Finance.
- **Trading Strategy**: Example strategy that evaluates market data to decide when to buy or sell.
- **Risk Management**: Evaluate risk based on predefined thresholds and ensure safe trading.
- **Order Execution**: Simulate placing orders with logging for audit trails.
- **Market Connection**: Simulate connecting to a market provider for real-time data.

## Requirements

- Python 3.7 or later
- Install the required dependencies:

```bash
pip install -r requirements.txt
```

### Requirements in `requirements.txt`:
- `pandas`
- `yfinance`
- `time`
- `numpy`
- `matplotlib`
- `pytest`

## Setup

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/core-monolith.git
   cd core-monolith
   ```

2. **Create a Virtual Environment** (recommended):

   ```bash
   python -m venv venv
   source venv/bin/activate  # On Windows use `venv\Scripts\activate`
   ```

3. **Install Dependencies**:

   ```bash
   pip install -r requirements.txt
   ```

4. **Run the Application**:

   Execute the `main.py` script to simulate the trading system:

   ```bash
   python main.py
   ```

## Example Usage

### Initialization

```bash
Initializing core monolith application...

DataHandler initialized.
OrderExecution initialized.
MarketConnector initialized.
RiskManager initialized.
TradingEngine initialized.
TradingStrategy initialized.
```

### Fetching Historical Market Data

```bash
Loading historical market data...
Fetching market data for AAPL from 2023-01-01 to 2023-01-10...

[*********************100%***********************]  1 of 1 completed
Loaded 5 data points for symbol 'AAPL'.
```

### Market Connection

```bash
Establishing market connection...
Connecting to market data provider...
Failed to connect to market data provider. Retrying...
Reconnecting attempt 1...
Reconnection failed. Retrying...
Reconnecting attempt 2...
Reconnection successful!
Market connection established.
```

### Trading Strategy Execution

```bash
Executing trading strategy...

At 2023-01-03: Market snapshot - {'open': Ticker
AAPL    130.279999
Name: 2023-01-03 00:00:00+00:00, dtype: float64, 'high': Ticker
AAPL    130.899994
Name: 2023-01-03 00:00:00+00:00, dtype: float64, 'low': Ticker
AAPL    124.169998
Name: 2023-01-03 00:00:00+00:00, dtype: float64, 'close': Ticker
AAPL    125.07
Name: 2023-01-03 00:00:00+00:00, dtype: float64, 'volume': Ticker
AAPL    112117500.0
Name: 2023-01-03 00:00:00+00:00, dtype: float64}

At 2023-01-03, no action taken.
At 2023-01-04: Market snapshot - {'open': Ticker
AAPL    126.889999
Name: 2023-01-04 00:00:00+00:00, dtype: float64, 'high': Ticker
AAPL    128.660004
Name: 2023-01-04 00:00:00+00:00, dtype: float64, 'low': Ticker
AAPL    125.080002
Name: 2023-01-04 00:00:00+00:00, dtype: float64, 'close': Ticker
AAPL    126.360001
Name: 2023-01-04 00:00:00+00:00, dtype: float64, 'volume': Ticker
AAPL    89113600.0
Name: 2023-01-04 00:00:00+00:00, dtype: float64}

At 2023-01-04, no action taken.
At 2023-01-05: Market snapshot - {'open': Ticker
AAPL    127.129997
Name: 2023-01-05 00:00:00+00:00, dtype: float64, 'high': Ticker
AAPL    127.769997
Name: 2023-01-05 00:00:00+00:00, dtype: float64, 'low': Ticker
AAPL    124.760002
Name: 2023-01-05 00:00:00+00:00, dtype: float64, 'close': Ticker
AAPL    125.019997
Name: 2023-01-05 00:00:00+00:00, dtype: float64, 'volume': Ticker
AAPL    80962700.0
Name: 2023-01-05 00:00:00+00:00, dtype: float64}

At 2023-01-05, no action taken.
At 2023-01-06: Market snapshot - {'open': Ticker
AAPL    126.010002
Name: 2023-01-06 00:00:00+00:00, dtype: float64, 'high': Ticker
AAPL    130.289993
Name: 2023-01-06 00:00:00+00:00, dtype: float64, 'low': Ticker
AAPL    124.889999
Name: 2023-01-06 00:00:00+00:00, dtype: float64, 'close': Ticker
AAPL    129.619995
Name: 2023-01-06 00:00:00+00:00, dtype: float64, 'volume': Ticker
AAPL    87754700.0
Name: 2023-01-06 00:00:00+00:00, dtype: float64}

At 2023-01-06, no action taken.
At 2023-01-09: Market snapshot - {'open': Ticker
AAPL    130.470001
Name: 2023-01-09 00:00:00+00:00, dtype: float64, 'high': Ticker
AAPL    133.410004
Name: 2023-01-09 00:00:00+00:00, dtype: float64, 'low': Ticker
AAPL    129.889999
Name: 2023-01-09 00:00:00+00:00, dtype: float64, 'close': Ticker
AAPL    130.149994
Name: 2023-01-09 00:00:00+00:00, dtype: float64, 'volume': Ticker
AAPL    70790800.0
Name: 2023-01-09 00:00:00+00:00, dtype: float64}

At 2023-01-09, no action taken.

Trading session completed.
Running final risk checks and reporting...

Core monolith process has finished.
```

### Explanation of Example Output
- **Initialization**: The core monolith application is initialized with various components such as `DataHandler`, `OrderExecution`, `MarketConnector`, `RiskManager`, `TradingEngine`, and `TradingStrategy`.
- **Market Data Fetching**: Historical market data for the symbol `AAPL` is fetched from Yahoo Finance for the period `2023-01-01` to `2023-01-10`.
- **Market Connection**: The application simulates connecting to a market data provider. It retries in case of failure, and eventually establishes a successful connection.
- **Strategy Execution**: A trading strategy is executed for each market snapshot. In this example, no action is taken for any of the dates, but in a more complex strategy, buy or sell orders could be triggered.
- **Risk Management**: Throughout the trading session, the system evaluates risk, ensuring that the trading logic follows safety constraints.
- **Completion**: Once all the trading decisions have been made, the system completes the session and reports the final risk analysis.
