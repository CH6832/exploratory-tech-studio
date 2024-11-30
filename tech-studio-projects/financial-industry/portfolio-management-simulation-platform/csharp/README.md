# Portfolio Management CLI Tool

The Portfolio Management CLI Tool is a command-line application designed to help users manage portfolios, implement trading strategies, and ingest financial data. This project is a simulation system that provides tools to analyze portfolio performance, test strategies, and visualize historical data.

---

## Table of Contents
- [Portfolio Management CLI Tool](#portfolio-management-cli-tool)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Documentation](#documentation)
  - [Building and Debugging](#building-and-debugging)
    - [Requirements](#requirements)
    - [Steps to Build](#steps-to-build)
    - [Steps to Debug](#steps-to-debug)
  - [Command-Line Examples](#command-line-examples)
    - [Portfolio Management](#portfolio-management)
      - [Example 1: Creating a Portfolio](#example-1-creating-a-portfolio)
      - [Example 2: Viewing Portfolio Summary](#example-2-viewing-portfolio-summary)
    - [Strategy Commands](#strategy-commands)
      - [Example 1: Adding a Trading Strategy](#example-1-adding-a-trading-strategy)
    - [Data Ingestion](#data-ingestion)
      - [Example 1: Ingesting Market Data](#example-1-ingesting-market-data)
  - [Technologies Used](#technologies-used)
  - [License](#license)

---

## Introduction

The CLI tool is built with a modular architecture to facilitate maintainability and extensibility. It enables users to:
- Create and manage portfolios.
- View portfolio summaries and histories.
- Test and implement trading strategies.
- Ingest market data for analysis and strategy optimization.

---

## Documentation

For more detailed documentation, refer to the [Project Documentation](https://yourdocumentationlink.example.com).

---

## Building and Debugging

### Requirements
- **IDE**: [Visual Studio 2022](https://visualstudio.microsoft.com/vs/) (or newer)
- **Operating System**: Windows 10 or later
- **.NET SDK**: [.NET 6.0+](https://dotnet.microsoft.com/download/dotnet/6.0)

### Steps to Build
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/portfolio-management-cli.git
   cd portfolio-management-cli
   ```
2. Open the solution file (`PortfolioManagement.sln`) in Visual Studio.
3. Build the project by pressing `Ctrl+Shift+B` or selecting `Build > Build Solution`.

### Steps to Debug
1. Open the `Program.cs` file in Visual Studio.
2. Set breakpoints where you want to inspect the code.
3. Press `F5` to start debugging.

---

## Command-Line Examples

### Portfolio Management

#### Example 1: Creating a Portfolio
```bash
corecli portfolio create --name "Tech Fund" --balance 100000
```
**Output:**
```
Starting the Portfolio Management CLI Tool...
Processing command: portfolio
Delegating to PortfolioCommandHandler...
PortfolioCommandHandler is processing the request...
Portfolio operations in progress...
Portfolio created successfully!
Portfolio Name: Tech Fund
Initial Balance: $100,000.00
```

#### Example 2: Viewing Portfolio Summary
```bash
corecli portfolio summary
```
**Output:**
```
Fetching portfolio summary...

Total Value: $150,000.00
Profit/Loss: $50,000.00
Positions:
- AAPL: 100 units @ $150.00 each (Value: $15,000.00)
- TSLA: 50 units @ $1,000.00 each (Value: $50,000.00)
- AMZN: 20 units @ $2,000.00 each (Value: $40,000.00)

Portfolio summary displayed successfully.
```

---

### Strategy Commands

#### Example 1: Adding a Trading Strategy
```bash
corecli strategy add --name "Momentum Strategy" --logic "if price > sma then buy"
```
**Output:**
```
Starting the Portfolio Management CLI Tool...
Processing command: strategy
Delegating to StrategyCommandHandler...
StrategyCommandHandler is processing the request...
Strategy operations in progress...
Strategy added successfully!
Strategy Name: Momentum Strategy
Logic: if price > sma then buy
```

---

### Data Ingestion

#### Example 1: Ingesting Market Data
```bash
corecli data ingest --source "NASDAQ" --type "historical"
```
**Output:**
```
Starting the Portfolio Management CLI Tool...
Processing command: data
Delegating to DataIngestionCommandHandler...
DataIngestionCommandHandler is processing the request...
Data ingestion operations in progress...
Market data from NASDAQ (historical) ingested successfully!
```

---

## Technologies Used

- **.NET 6.0** ([Documentation](https://docs.microsoft.com/en-us/dotnet/))
- **Visual Studio 2022** ([Documentation](https://visualstudio.microsoft.com/vs/))
- **Windows 10** ([Documentation](https://www.microsoft.com/en-us/windows/))
- **C# Programming Language** ([Documentation](https://learn.microsoft.com/en-us/dotnet/csharp/))

---

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
