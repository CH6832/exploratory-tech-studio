package com.cms.search;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Repository for interacting with MongoDB to perform CRUD operations on search index.
 */
public interface SearchIndexRepository extends MongoRepository<SearchIndex, String> {
    
    /**
     * Custom query to search the search index by title or content.
     * @param query Search term to find in title or content.
     * @return List of search results.
     */
    List<SearchIndex> findByTitleContainingOrContentContaining(String query, String query2);
}
