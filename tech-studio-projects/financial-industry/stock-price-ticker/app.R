library(shiny)

# Source UI and Server code
source("ui.R")
source("server.R")

# Run the application 
shinyApp(ui = ui, server = server)
