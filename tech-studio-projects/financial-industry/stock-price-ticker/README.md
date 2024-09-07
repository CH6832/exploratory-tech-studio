# Stock Analysis Dashboard

## Project Description

The **Stock Analysis Dashboard** is a Shiny application that allows users to visualize historical stock price data and moving averages. Users can input a stock ticker symbol, select a date range, and specify periods for short-term and long-term moving averages. The app fetches historical stock data from Yahoo Finance, calculates the specified moving averages, and displays a plot showing the stock's adjusted prices along with the moving averages.

## Features

- **Input Fields**:
  - **Stock Ticker Symbol**: Enter the stock symbol (e.g., AAPL for Apple Inc.).
  - **Date Range**: Choose the start and end dates for the historical data.
  - **Short Moving Average (days)**: Specify the number of days for the short-term moving average.
  - **Long Moving Average (days)**: Specify the number of days for the long-term moving average.

- **Output**:
  - A plot displaying:
    - The adjusted closing prices of the selected stock.
    - Short-term and long-term moving averages.

## Installation

To set up the Stock Analysis Dashboard on your local machine, follow these steps:

1. **Install R and RStudio**:
   Ensure you have R and RStudio installed. You can download them from:
   - [R](https://cran.r-project.org/)
   - [RStudio](https://www.rstudio.com/products/rstudio/download/)

2. **Install Required R Packages**:
   Open RStudio and install the necessary R packages by running the following command in the R console:

   ```r
   install.packages(c("shiny", "quantmod", "ggplot2", "TTR"))
   ```

3. **Download Project Files**:
   Clone or download the project repository from GitHub or any other source where the project is hosted. Ensure you have the following files:
   - `ui.R`
   - `server.R`
   - `app.R`

4. **Run the Application**:
   In RStudio, open the `app.R` file and run the application using the following command in the R console:

   ```r
   shiny::runApp("path/to/your/app.R")
   ```

   Replace `"path/to/your/app.R"` with the actual path to your `app.R` file.

## Usage

1. **Launch the App**:
   Running the `app.R` file will open the Stock Analysis Dashboard in your web browser.

2. **Input Stock Ticker**:
   Enter the stock ticker symbol of the company you want to analyze (e.g., `AAPL` for Apple).

3. **Select Date Range**:
   Choose the start and end dates for the historical data you wish to view.

4. **Specify Moving Averages**:
   Set the number of days for the short-term and long-term moving averages.

5. **View the Plot**:
   The application will display a plot showing the stock's adjusted prices and the calculated moving averages over the specified date range.

## File Structure

- **`ui.R`**: Defines the user interface of the Shiny app.
- **`server.R`**: Contains the server-side logic, including data retrieval and plot generation.
- **`app.R`**: Initializes and runs the Shiny application by sourcing `ui.R` and `server.R`.
