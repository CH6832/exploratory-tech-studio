package com.efilingusgaap.financialfilingservice;

/**
 * Represents a Filing entity in the financial filing service.
 *
 * A Filing is a basic record containing essential details about a financial
 * filing, including a unique identifier, a title, and a description.
 * This class can be extended to include additional attributes as needed.
 */
public class Filing {

    /**
     * The unique identifier for this filing.
     */
    private Long id;

    /**
     * The title of the filing.
     */
    private String title;

    /**
     * A description providing additional details about the filing.
     */
    private String description;

    /**
     * Gets the unique identifier of this filing.
     *
     * @return the id of the filing
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this filing.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of this filing.
     *
     * @return the title of the filing
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this filing.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of this filing.
     *
     * @return the description of the filing
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this filing.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
