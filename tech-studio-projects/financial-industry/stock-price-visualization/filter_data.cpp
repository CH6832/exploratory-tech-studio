#include <Rcpp.h>
using namespace Rcpp;

// [[Rcpp::export]]
DataFrame filter_data(DataFrame data, String ticker, String start_date, String end_date) {
  // Extract columns from the DataFrame
  CharacterVector tickers = data["ticker"];
  DateVector dates = data["date"];
  NumericVector close_prices = data["close"];
  NumericVector volumes = data["volume"];
  
  // Create filters based on ticker and date range
  LogicalVector ticker_filter;
  if (ticker != "All") {
    ticker_filter = (tickers = ticker);
  } else {
    ticker_filter = LogicalVector(tickers.size(), true); // All entries are TRUE
  }
  
  LogicalVector date_filter = (dates >= start_date) & (dates <= end_date);
  
  // Combine filters
  LogicalVector combined_filter = ticker_filter & date_filter;
  
  // Return filtered DataFrame
  return DataFrame::create(
    Named("date") = dates[combined_filter],
                         Named("close") = close_prices[combined_filter],
                                                      Named("volume") = volumes[combined_filter],
                                                                               Named("ticker") = tickers[combined_filter]
  );
}
