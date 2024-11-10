package com.cms.search;

import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class responsible for performing search functionality with MongoDB integration.
 */
@Service
public class SearchService {

    private final SearchIndexRepository searchIndexRepository;

    public SearchService(SearchIndexRepository searchIndexRepository) {
        this.searchIndexRepository = searchIndexRepository;
    }

    /**
     * Method that handles the search functionality with MongoDB.
     * It searches for the query in the title or content of the search index collection.
     * 
     * @param query The search query string.
     * @return SearchResult object containing the search results.
     * @throws SearchServiceException Custom exception thrown in case of search failures.
     */
    public SearchResult search(String query) {
        try {
            if (query == null || query.isEmpty()) {
                throw new SearchServiceException("Query cannot be null or empty.");
            }

            // Perform search in MongoDB's SearchIndex collection
            List<SearchIndex> searchResults = searchIndexRepository.findByTitleContainingOrContentContaining(query, query);

            // If no results are found
            if (searchResults.isEmpty()) {
                throw new SearchServiceException("No results found for query: " + query);
            }

            // Return results as a SearchResult object
            SearchResult result = new SearchResult();
            result.setQuery(query);
            result.setResult("Found " + searchResults.size() + " result(s).");

            return result;
        } catch (Exception e) {
            throw new SearchServiceException("An error occurred while processing the search query: " + e.getMessage(), e);
        }
    }
}