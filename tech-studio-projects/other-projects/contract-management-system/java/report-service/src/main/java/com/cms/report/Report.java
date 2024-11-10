package com.cms.report;

import java.util.function.IntPredicate;

/**
 * The Report class is a simple representation of a report.
 * It contains the name and the content of the report.
 */
public class Report {

    private String name;  // Name of the report
    private String content;  // Content of the report

    /**
     * Constructor to initialize a report with name and content.
     *
     * @param name    The name of the report
     * @param content The content of the report
     */
    public Report(String name, String content) {
        this.name = name;
        this.content = content;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public IntPredicate getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}
