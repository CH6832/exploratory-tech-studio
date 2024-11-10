package com.cms.search;

/**
 * Model class to represent the result of a search query.
 * <p>
 * This class contains both the search query string used in a search operation
 * and the resulting data (as a string) that matches the query.
 * </p>
 * <p>
 * This class can be used to encapsulate search results and facilitate data transfer
 * between the search logic and other layers or components in the application.
 * </p>
 */
public class SearchResult {

    // The query string used to search
    private String query;

    // The result data or information returned from the search, represented as a string
    private String result;

    /**
     * Gets the search query string.
     * 
     * @return the query string used in the search
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the search query string.
     * 
     * @param query the query string used in the search
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Gets the result of the search.
     * 
     * @return the result data as a string
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the result of the search.
     * 
     * @param result the data or information obtained from the search query
     */
    public void setResult(String result) {
        this.result = result;
    }
}
