cmake_minimum_required(VERSION 3.8)

add_library(trading_engine_server STATIC
    TradingEngineServer.cpp
    TradingEngineServerConfiguration.cpp
    TradingEngineServerHostBuilder.cpp
    TradingEngineServerServiceProvider.cpp
)

target_include_directories(trading_engine_server PUBLIC
    ${CMAKE_CURRENT_SOURCE_DIR}/include
)
