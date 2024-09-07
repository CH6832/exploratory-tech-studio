library(shiny)
library(ggplot2)
library(DT)
library(Rcpp)

# Source the C++ file
sourceCpp("filter_data.cpp")

# Sample sales data for demonstration purposes
sales_data <- data.frame(
  date = rep(seq(as.Date('2023-01-01'), as.Date('2023-12-31'), by="month"), 3),
  category = rep(c("Category1", "Category2", "Category3"), each = 12),
  sales = sample(100:1000, 36, replace = TRUE),
  product = rep(c("ProductA", "ProductB", "ProductC"), each = 12)
)

ui <- fluidPage(
  titlePanel("Sales Dashboard"),
  sidebarLayout(
    sidebarPanel(
      selectInput("category", "Category:", choices = c("All", "Category1", "Category2", "Category3")),
      dateRangeInput("date_range", "Date Range:"),
      selectInput("product", "Product:", choices = c("All", "ProductA", "ProductB", "ProductC")),
      downloadButton("downloadData", "Download Filtered Data")
    ),
    mainPanel(
      plotOutput("sales_plot"),
      DTOutput("sales_table")
    )
  )
)

server <- function(input, output) {
  
  filtered_data <- reactive({
    filter_data(sales_data, input$category, input$date_range[1], input$date_range[2], input$product)
  })
  
  output$sales_plot <- renderPlot({
    data <- filtered_data()
    
    ggplot(data, aes(x = date, y = sales)) +
      geom_line() +
      labs(title = "Sales Over Time")
  })
  
  output$sales_table <- renderDT({
    data <- filtered_data()
    summary_data <- data.frame(
      Category = unique(data$category),
      Total_Sales = sum(data$sales),
      Average_Sales = mean(data$sales)
    )
    datatable(summary_data)
  })
  
  output$downloadData <- downloadHandler(
    filename = function() {
      paste("filtered_sales_data-", Sys.Date(), ".csv", sep="")
    },
    content = function(file) {
      write.csv(filtered_data(), file, row.names = FALSE)
    }
  )
}

shinyApp(ui, server)
