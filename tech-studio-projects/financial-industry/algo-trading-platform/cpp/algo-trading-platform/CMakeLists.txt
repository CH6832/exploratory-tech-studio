# CMakeList.txt : CMake project for algo-trading-platform, include source and define
# project specific logic here.
#

# Add source to this project's executable.
# add_executable (algo-trading-platform "algo-trading-platform.cpp"  "../trading-engine-server/TradingEngineServer.cpp" "../trading-engine-server/TradingEngineServer.h"  "../trading-engine-server/ITradingEngineServer.h" "../trading-engine-server/TradingEngineServerConfiguration.cpp" "../trading-engine-server/TradingEngineServerConfiguration.h" "../trading-engine-server/TradingEngineServerHostBuilder.cpp" "../trading-engine-server/TradingEngineServerHostBuilder.h" "../trading-engine-server/TradingEngineServerServiceProvider.cpp" "../logging/ILogger.h" "../logging/ConsoleLogger.h" )

add_executable(algo-trading-platform
    algo-trading-platform.cpp
)

if (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(TARGET algo-trading-platform PROPERTY CXX_STANDARD 20)
endif()

target_link_libraries(algo-trading-platform PRIVATE
    trading_engine_server
    logging
)

target_include_directories(algo-trading-platform PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}/include
)

# TODO: Add tests and install targets if needed.
