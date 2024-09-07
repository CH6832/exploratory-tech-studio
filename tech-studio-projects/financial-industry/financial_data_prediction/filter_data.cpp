#include <Rcpp.h>
using namespace Rcpp;

// [[Rcpp::export]]
DataFrame filter_data(DataFrame data, std::string category, std::string start_date, std::string end_date) {
  
  // Extract columns from the DataFrame
  CharacterVector categories = data["category"];
  DateVector dates = data["date"];
  NumericVector sales = data["sales"];
  
  // Convert start_date and end_date from std::string to Date
  Date start_date_obj(start_date);
  Date end_date_obj(end_date);
  
  // Create filters
  LogicalVector category_filter;
  if (category != "All") {
    CharacterVector category_to_compare = CharacterVector::create(category);
    category_filter = (categories == category_to_compare[0]);
  } else {
    category_filter = rep(true, categories.size());
  }
  
  // Create date filter by comparing Date objects
  LogicalVector date_filter = (dates >= start_date_obj) & (dates <= end_date_obj);
  
  // Combine filters
  LogicalVector combined_filter = category_filter & date_filter;
  
  // Apply filter and create new DataFrame
  return DataFrame::create(
    Named("date") = dates[combined_filter],
                         Named("sales") = sales[combined_filter],
                                               Named("category") = categories[combined_filter]
  );
}
