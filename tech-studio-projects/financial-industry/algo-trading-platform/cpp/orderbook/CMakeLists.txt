cmake_minimum_required(VERSION 3.8)

add_library(orderbook STATIC
    MatchResult.cpp
    Orderbook.cpp
    OrderStatusCreator.cpp
    OrderbookSpread.cpp
)

target_include_directories(orderbook PUBLIC
    ${CMAKE_CURRENT_SOURCE_DIR}/include
)
