package com.cms.logging;

import java.time.ZonedDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logs")  // Correct for MongoDB
public class LogEntry {
	private String id;
    private String message;
    private ZonedDateTime timestamp;
    // Getters, setters, etc.
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getLevel() {
		// TODO Auto-generated method stub
		return null;
	}
}
