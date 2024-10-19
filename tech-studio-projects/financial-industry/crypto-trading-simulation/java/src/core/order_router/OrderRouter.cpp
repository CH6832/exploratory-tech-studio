#include "OrderRouter.h"
#include <curl/curl.h>
#include "../utils/Config.h"
#include "../utils/Logger.h"
#include "../utils/HmacSha256.h"

void OrderRouter::placeOrder(const std::string& symbol, const std::string& side, double quantity, double price) {
    CURL* curl = curl_easy_init();
    if(curl) {
        // Load API keys from config.json
        std::string api_key = Config::get("BINANCE_API_KEY");
        std::string api_secret = Config::get("BINANCE_API_SECRET");

        if (api_key.empty() || api_secret.empty()) {
            Logger::logError("API keys are missing in config");
            curl_easy_cleanup(curl);
            return;
        }

        // Construct the order payload
        std::string query = "symbol=" + symbol + "&side=" + side + "&type=LIMIT&timeInForce=GTC";
        query += "&quantity=" + std::to_string(quantity) + "&price=" + std::to_string(price);
        query += "&timestamp=" + std::to_string(time(NULL) * 1000);

        // Sign the request
        std::string signature = hmac_sha256(api_secret, query);
        query += "&signature=" + signature;

        // Set the API endpoint and headers
        std::string url = "https://api.binance.com/api/v3/order";
        curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, query.c_str());
        struct curl_slist* headers = NULL;
        headers = curl_slist_append(headers, ("X-MBX-APIKEY: " + api_key).c_str());
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

        // Perform the request and handle response
        CURLcode res = curl_easy_perform(curl);
        if(res != CURLE_OK) {
            Logger::logError("Failed to place order: " + std::string(curl_easy_strerror(res)));
        } else {
            Logger::logInfo("Order placed successfully.");
        }
        
        curl_easy_cleanup(curl);
    }
}
