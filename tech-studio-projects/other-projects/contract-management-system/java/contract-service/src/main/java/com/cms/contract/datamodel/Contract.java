package com.cms.contract.datamodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

/**
 * Represents a contract entity stored in MongoDB.
 * <p>
 * This class contains the core details of a contract, such as title, description,
 * parties involved, and associated approvals. It also contains nested classes for
 * various contract-related sub-objects like Party, Approval, and DocumentInfo.
 * </p>
 */
@Document(collection = "contracts")
public class Contract {

    @Id
    private String id;  // Unique identifier for the contract
    private String title;  // Title of the contract
    private String description;  // Description of the contract
    private List<Party> parties;  // List of parties involved in the contract
    private String status;  // Current status of the contract (e.g., active, expired)
    private List<String> terms;  // Terms associated with the contract
    private List<Approval> approvals;  // List of approvals for the contract
    @JsonProperty("pricing")
    private Pricing pricing;  // Pricing details for the contract
    private List<DocumentInfo> documents;  // Associated documents for the contract
    private List<AuditLog> auditLog;  // Audit logs for contract actions
    private List<Notification> notifications;  // Notifications associated with the contract

    // Constructors, getters, and setters omitted for brevity

    /**
     * Nested class representing a party involved in the contract.
     */
    public static class Party {
        private String name;  // Name of the party
        private String role;  // Role of the party (e.g., buyer, seller)

        // Getters and setters for Party class
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    /**
     * Nested class representing contract approval details.
     */
    public static class Approval {
        private String approver;  // Name of the person who approved the contract
        private String role;  // Role of the approver
        private Date approvedDate;  // Date the contract was approved
        private String status;  // Approval status (e.g., approved, rejected)

        // Getters and setters for Approval class
        public String getApprover() {
            return approver;
        }

        public void setApprover(String approver) {
            this.approver = approver;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Date getApprovedDate() {
            return approvedDate;
        }

        public void setApprovedDate(Date approvedDate) {
            this.approvedDate = approvedDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    /**
     * Nested class representing pricing details associated with the contract.
     */
    public static class Pricing {
        private Integer baseRate;  // Base rate of the contract
        private String currency;  // Currency used for the pricing
        private String paymentTerms;  // Payment terms for the contract

        // Getters and setters for Pricing class
        public Integer getBaseRate() {
            return baseRate;
        }

        public void setBaseRate(Integer baseRate) {
            this.baseRate = baseRate;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPaymentTerms() {
            return paymentTerms;
        }

        public void setPaymentTerms(String paymentTerms) {
            this.paymentTerms = paymentTerms;
        }
    }

    /**
     * Nested class representing document information related to the contract.
     */
    public static class DocumentInfo {
        private String filename;  // Name of the document file
        private String url;  // URL to access the document
        private Date uploadedDate;  // Date the document was uploaded
        private int version;  // Version number of the document

        // Getters and setters for DocumentInfo class
        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Date getUploadedDate() {
            return uploadedDate;
        }

        public void setUploadedDate(Date uploadedDate) {
            this.uploadedDate = uploadedDate;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }

    /**
     * Nested class representing an audit log entry for the contract.
     */
    public static class AuditLog {
        private String action;  // Action performed (e.g., contract created, updated)
        private String user;  // User who performed the action
        private Date timestamp;  // Timestamp of the action

        // Getters and setters for AuditLog class
        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }
    }

    /**
     * Nested class representing a notification related to the contract.
     */
    public static class Notification {
        private String type;  // Type of notification (e.g., alert, reminder)
        private Date sendDate;  // Date when the notification was sent
        private String status;  // Status of the notification (e.g., sent, pending)

        // Getters and setters for Notification class
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Date getSendDate() {
            return sendDate;
        }

        public void setSendDate(Date sendDate) {
            this.sendDate = sendDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    /**
     * Nested class representing the terms of the contract.
     */
    public static class Terms {
        private String scope;  // Scope of the contract

        // Getters and setters for Terms class
        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
    }

    // Getters and setters for the Contract class

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public List<String> getTerms() {
        return terms;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public List<Approval> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public List<DocumentInfo> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentInfo> documents) {
        this.documents = documents;
    }

    public List<AuditLog> getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(List<AuditLog> auditLog) {
        this.auditLog = auditLog;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
