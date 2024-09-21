#include "../trading-engine-server/TradingEngineServerHostBuilder.h"
#include "../trading-engine-server/TradingEngineServerServiceProvider.h"
#include <future>
#include <iostream>

using namespace std;

int main()
{
    try {
        // Build the trading engine server
        auto engine = TradingEngineServerHostBuilder::BuildTradingEngineServer();

        // Register the engine's services in the global service provider
        TradingEngineServerServiceProvider::RegisterService(engine);

        // Creating a scope (In C++, we don't have scope management like in C#, but we can simulate it)
        {
            // Run the engine (this will block until the server is stopped)
            future<void> runTask = engine->Run(shared_future<void>{}); // Passing an empty shared_future as a default cancellation token

            // Wait for the engine to finish (In real applications, we would handle shutdown signals properly)
            runTask.get();
        }
    }
    catch (const exception& e) {
        cerr << "Exception: " << e.what() << endl;
        return 1;
    }
    catch (...) {
        cerr << "Unknown exception occurred" << endl;
        return 1;
    }

    return 0;
}
