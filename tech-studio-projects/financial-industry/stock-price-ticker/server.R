library(shiny)
library(quantmod)
library(ggplot2)
library(TTR)

# Define Server
server <- function(input, output) {
  
  # Reactive expression to fetch stock data
  stock_data <- reactive({
    req(input$ticker)  # Ensure ticker is available
    getSymbols(input$ticker, src = "yahoo", from = input$date_range[1], to = input$date_range[2], auto.assign = FALSE)
  })
  
  # Render plot based on stock data
  output$stock_plot <- renderPlot({
    data <- stock_data()
    
    # Convert to data frame
    data <- data.frame(Date = index(data), coredata(data))
    colnames(data)[5] <- "Adjusted"
    
    # Calculate Moving Averages
    data$MA_Short <- SMA(data$Adjusted, n = input$ma_short)
    data$MA_Long <- SMA(data$Adjusted, n = input$ma_long)
    
    # Plot
    ggplot(data, aes(x = Date)) +
      geom_line(aes(y = Adjusted, color = "Adjusted")) +
      geom_line(aes(y = MA_Short, color = paste0("MA_", input$ma_short))) +
      geom_line(aes(y = MA_Long, color = paste0("MA_", input$ma_long))) +
      labs(title = paste("Stock Prices for", input$ticker),
           y = "Price",
           color = "Legend") +
      theme_minimal()
  })
}
