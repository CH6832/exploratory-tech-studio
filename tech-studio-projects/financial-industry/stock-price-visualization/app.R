library(shiny)
library(ggplot2)
library(DT)
library(Rcpp)

# Source the C++ file
sourceCpp("filter_data.cpp")

# Load the stock data
stock_data <- read.csv("data/stock_data.csv")

ui <- fluidPage(
  titlePanel("Financial Dashboard"),
  sidebarLayout(
    sidebarPanel(
      selectInput("ticker", "Ticker:", choices = c("All", unique(stock_data$ticker))),
      dateRangeInput("date_range", "Date Range:", start = min(stock_data$date), end = max(stock_data$date)),
      downloadButton("downloadData", "Download Filtered Data")
    ),
    mainPanel(
      plotOutput("price_plot"),
      DTOutput("summary_table")
    )
  )
)

server <- function(input, output) {
  
  filtered_data <- reactive({
    filter_data(stock_data, input$ticker, input$date_range[1], input$date_range[2])
  })
  
  output$price_plot <- renderPlot({
    data <- filtered_data()
    
    ggplot(data, aes(x = date, y = close)) +
      geom_line() +
      labs(title = paste("Closing Prices of", input$ticker), x = "Date", y = "Closing Price")
  })
  
  output$summary_table <- renderDT({
    data <- filtered_data()
    summary_data <- data.frame(
      Ticker = unique(data$ticker),
      Total_Volume = sum(data$volume),
      Average_Close = mean(data$close)
    )
    datatable(summary_data)
  })
  
  output$downloadData <- downloadHandler(
    filename = function() {
      paste("filtered_stock_data-", Sys.Date(), ".csv", sep="")
    },
    content = function(file) {
      write.csv(filtered_data(), file, row.names = FALSE)
    }
  )
}

shinyApp(ui, server)
