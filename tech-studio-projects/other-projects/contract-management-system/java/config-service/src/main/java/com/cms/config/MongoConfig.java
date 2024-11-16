package com.cms.config;

import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MongoDB configuration class for setting up the MongoTemplate bean.
 * This configuration class ensures that Spring Boot correctly configures
 * the connection to MongoDB and integrates it with the application's data layer.
 */
@Configuration
public class MongoConfig {

    /**
     * Bean definition for MongoTemplate.
     * MongoTemplate is a central class for interacting with MongoDB in Spring Data MongoDB.
     * It provides methods for CRUD operations, as well as query creation and execution.
     *
     * @param mongoDatabaseFactory The factory to create the MongoDatabase instance.
     * @param converter The converter for mapping between MongoDB documents and Java objects.
     * @return A MongoTemplate instance configured with the provided factory and converter.
     */
    @Bean
    MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory, MappingMongoConverter converter) {
        // Create and return a new MongoTemplate using the provided MongoDatabaseFactory and MappingMongoConverter
        return new MongoTemplate(mongoDatabaseFactory, converter);
    }
}
