package com.example.contractmanagement.contractservice;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Table;

@EntityScan
@Table("contracts") // Specify the table name if needed
public class Contract {

    @Id
    private Long id;

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal budget;

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
