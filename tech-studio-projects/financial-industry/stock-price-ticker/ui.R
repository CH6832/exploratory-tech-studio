library(shiny)

# Define UI
ui <- fluidPage(
  titlePanel("Stock Analysis Dashboard"),
  
  sidebarLayout(
    sidebarPanel(
      textInput("ticker", "Stock Ticker Symbol:", value = "AAPL"),
      dateRangeInput("date_range", "Date Range:",
                     start = Sys.Date() - 30,
                     end = Sys.Date()),
      numericInput("ma_short", "Short Moving Average (days):", value = 20, min = 1),
      numericInput("ma_long", "Long Moving Average (days):", value = 50, min = 1)
    ),
    
    mainPanel(
      plotOutput("stock_plot")
    )
  )
)
