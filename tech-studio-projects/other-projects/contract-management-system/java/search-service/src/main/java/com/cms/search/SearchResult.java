package com.cms.search;

/**
 * Model class to represent the result of a search query.
 * <p>
 * This class contains the search query and the results returned for that query.
 * </p>
 */
public class SearchResult {

    private String query;
    private String result;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
