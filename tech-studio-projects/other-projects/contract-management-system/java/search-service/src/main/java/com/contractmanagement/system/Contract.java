package com.contractmanagement.system;

import org.springframework.data.annotation.Id;

@Document(indexName = "contracts")
public class Contract {
    
    @Id
    private String id;
    private String title;
    private String description;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}