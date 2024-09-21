#define CATCH_CONFIG_MAIN
#include "./catch2/catch.hpp"
#include "httplib.hpp"  // Include the httplib header

// Test case to verify that the server responds correctly
TEST_CASE(R"(Server responds with HTML content)") {
    httplib::Client cli("localhost", 8088);  // Replace with your server's address and port

    auto res = cli.Get("/");  // Perform an HTTP GET request

    REQUIRE(res);  // Check that the response is not null
    REQUIRE(res->status == 200);  // Check that the status code is 200 OK
    REQUIRE(res->body.find("Hello, World!") != std::string::npos);  // Check if the response contains expected text
}
