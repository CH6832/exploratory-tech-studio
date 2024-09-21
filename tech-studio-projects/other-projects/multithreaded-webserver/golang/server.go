package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"os"

	"golang.org/x/time/rate"
)

type Server struct {
	port    int
	logger  *log.Logger
	limiter *rate.Limiter
}

func NewServer(port int) *Server {
	return &Server{
		port:    port,
		logger:  log.New(os.Stdout, "INFO: ", log.LstdFlags),
		limiter: rate.NewLimiter(5, 10), // 5 req/sec with a burst of 10
	}
}

func (s *Server) Start() {
	mux := http.NewServeMux()
	mux.HandleFunc("/", s.handleRequest)

	addr := fmt.Sprintf(":%d", s.port)
	server := &http.Server{
		Addr:    addr,
		Handler: mux,
	}

	s.logger.Printf("Server is listening on port %d\n", s.port)
	if err := server.ListenAndServe(); err != nil {
		s.logger.Fatalf("Failed to start server: %v", err)
	}
}

func (s *Server) handleRequest(w http.ResponseWriter, r *http.Request) {
	if !s.limiter.Allow() {
		http.Error(w, "Too Many Requests", http.StatusTooManyRequests)
		return
	}

	path := r.URL.Path
	if path == "/" {
		path = "/index.html"
	}
	filePath := "." + path

	fileContent, err := ioutil.ReadFile(filePath)
	if err != nil {
		s.logger.Printf("File not found: %s", filePath)
		http.Error(w, "404 Not Found", http.StatusNotFound)
		return
	}

	w.Header().Set("Content-Type", "text/html")
	w.WriteHeader(http.StatusOK)
	w.Write(fileContent)
}
