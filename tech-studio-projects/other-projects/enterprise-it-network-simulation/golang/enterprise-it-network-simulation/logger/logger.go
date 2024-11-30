package logger

import (
	"log"
	"os"
)

// Init initializes the logger
func Init() {
	logFile, err := os.OpenFile("simulation.log", os.O_CREATE|os.O_RDWR|os.O_APPEND, 0666)
	if err != nil {
		log.Fatal("Error opening log file: ", err)
	}
	log.SetOutput(logFile)
	log.Println("Logger initialized.")
}

// LogInfo logs information level messages
func LogInfo(message string) {
	log.Println("[INFO] " + message)
}

// LogError logs error level messages
func LogError(message string) {
	log.Println("[ERROR] " + message)
}
