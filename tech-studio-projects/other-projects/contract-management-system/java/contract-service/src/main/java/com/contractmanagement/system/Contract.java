package com.contractmanagement.system;

import org.springframework.data.neo4j.core.schema.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class Contract {
    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the primary key value
    private Long id;

    @Column(nullable = false) // Maps this field to a database column and ensures it's not null
    private String contractName;

    @Column(nullable = false)
    private String clientName;

    @Temporal(TemporalType.DATE) // Maps the date type to a SQL DATE
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private double value;

    // Default constructor
    public Contract() {}

    // Parameterized constructor
    public Contract(String contractName, String clientName, Date startDate, Date endDate, double value) {
        this.contractName = contractName;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
