# Makefile for building the market-making platform

CXX = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -O3
SRC = $(wildcard src/core/*.cpp src/network/*.cpp src/utils/*.cpp)
OBJ = $(SRC:.cpp=.o)
TARGET = market_maker

.PHONY: all clean deploy

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CXX) $(CXXFLAGS) -o $@ $^

clean:
	rm -f $(OBJ) $(TARGET)

deploy:
	./scripts/deploy.bat
