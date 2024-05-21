#!/usr/bin/env python3
# -*- coding: utf-8 -*-


"""app.py (stock price analysis dahsboard)"""


import dash
from dash import dcc, html
from dash.dependencies import Input, Output
import yfinance as yf
import plotly.graph_objs as go


# Initialize Dash app
app = dash.Dash(__name__)

# Define app layout
app.layout = html.Div([
    html.H1("Real-time Stock Price Analysis Dashboard"),
    html.Label("Enter stock symbols (comma-separated):"),
    dcc.Input(id="stock-input", type="text", value="AAPL,MSFT,GOOGL"),
    html.Button(id="submit-button", n_clicks=0, children="Submit"),
    dcc.Graph(id="stock-price-chart")
])

# Callback to update stock price chart
@app.callback(
    Output("stock-price-chart", "figure"),
    [Input("submit-button", "n_clicks")],
    [dash.dependencies.State("stock-input", "value")]
)
def update_stock_price_chart(n_clicks, stock_symbols):
    # Split entered symbols and fetch historical stock data for each symbol
    symbols = [s.strip() for s in stock_symbols.split(",")]
    data = []
    for symbol in symbols:
        stock_data = yf.download(symbol, start="2022-01-01", end="2022-12-31")
        trace = go.Candlestick(
            x=stock_data.index,
            open=stock_data["Open"],
            high=stock_data["High"],
            low=stock_data["Low"],
            close=stock_data["Close"],
            name=symbol
        )
        data.append(trace)

    # Create Plotly figure
    layout = {
        "title": "Real-time Stock Price Analysis",
        "xaxis": {"title": "Date"},
        "yaxis": {"title": "Price (USD)"},
        "showlegend": True
    }
    fig = go.Figure(data=data, layout=layout)

    return fig


if __name__ == "__main__":
    app.run_server(debug=True)
