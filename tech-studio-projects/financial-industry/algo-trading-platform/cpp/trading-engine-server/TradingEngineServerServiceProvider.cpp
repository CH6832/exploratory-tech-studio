#include "TradingEngineServerServiceProvider.h"

using namespace std;

// Initialize the static member
std::unordered_map<std::type_index, std::shared_ptr<void>> TradingEngineServerServiceProvider::services;
