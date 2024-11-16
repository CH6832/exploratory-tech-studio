package com.cms.contract;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.cms.contract.Contract.ContactDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "contracts")
public class Contract {
	
    @Id
    public String id;
    private String title;
    private String description;
    @JsonDeserialize(as = ArrayList.class)
    private List<Party> parties;
    private String status;
    @JsonDeserialize(as = ArrayList.class)
    private String terms;
    @JsonProperty
    private List<Approval> approvals;  // Ensure that this field is serialized as "pricing"
    @JsonProperty("pricing")
    private Pricing pricing;
    private List<DocumentInfo> documents;
    private List<AuditLog> auditLog;
    private List<Notification> notifications;
   

    // Getters and setters (not shown for brevity)
    
    // Nested static classes for sub-objects like Party, Dates, etc.

    public static class ContactDetails {
        private String email;
        private String phone;
        // Getters and setters
    }

    public class MongoDateDeserializer extends JsonDeserializer<String> {

        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode node = p.getCodec().readTree(p);
            JsonNode dateNode = node.get("$date");
            
            return dateNode != null ? dateNode.asText() : null;
        }
    }
    
    
    
    public static class Terms {
    	@JsonProperty
    	private String scope;
        // Getters and setters
    }

    public static class Pricing {
    	@JsonProperty
    	private Integer baseRate;
    	@JsonProperty
    	private String currency;
    	@JsonProperty
    	private String paymentTerms;
    }

    public static class Termination {
        private String noticePeriod;
        private Double terminationFee;
        // Getters and setters
    }

    public static class Approval {
        @JsonProperty
        private String approver;
        @JsonProperty
        private String role;
        @JsonProperty
        private Date approvedDate;
        @JsonProperty
        private String status;
    }

    public static class DocumentInfo {
    	@JsonProperty
        private String filename;
    	@JsonProperty
        private String url;
    	@JsonProperty
        private Date uploadedDate;
    	@JsonProperty
        private int version;
    }

    public static class AuditLog {
        private String action;
        private String user;
        private Date timestamp;
    }

    public static class Notification {
    	@JsonProperty
        private String type;
    	@JsonProperty
        private Date sendDate;
    	@JsonProperty
        private String status;
    }

	public static class Party {
	
	    private String name;
	    private String role;
	
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
	
    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }
	
	public void setId(String id) {
		this.id = id;
		
	}

	public Object getUpdatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	String getTitle() {
		return title;
	}

	private void setTitle(String title) {
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
	
	@JsonProperty("approverName")
	public List<Approval> getApprovals() {
		return approvals;
	}

	public void setApprovals(List<Approval> approvals) {
		this.approvals = approvals;
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
