package com.cms.contract;

/**
 * Event class representing an audit event for actions taken on contracts.
 * <p>
 * This class is used to capture and log contract-related actions for auditing purposes,
 * such as creating, updating, or deleting contracts. It records the ID of the contract,
 * the action type (e.g., "CREATE", "UPDATE", "DELETE"), the user who performed the action,
 * and the timestamp of when the action was performed.
 * </p>
 */
public class ContractAuditEvent {

    private String contractId;      // The unique identifier of the contract
    private String actionType;      // The type of action performed on the contract
    private String user;            // The user who performed the action
    private long timestamp;         // The timestamp when the action occurred
    private final String action;    // A descriptive message for the action, if needed

    /**
     * Primary constructor for creating a ContractAuditEvent with the specified contract ID,
     * action type, and user.
     * <p>
     * This constructor initializes all fields except the action, which is set to an empty
     * string by default. The timestamp is set to the current system time in milliseconds.
     * </p>
     *
     * @param contractService The ID of the contract associated with this event.
     * @param savedContract The type of action performed on the contract (e.g., "CREATE", "UPDATE").
     * @param user       The user who performed the action.
     */
    public ContractAuditEvent(String contractService, String savedContract, String user) {
        this.action = "";  // Action message can be optionally set later if needed
        this.contractId = contractService;
        this.actionType = savedContract;
        this.user = user;
        this.timestamp = System.currentTimeMillis();  // Capture the current timestamp
    }

    /**
     * Alternative constructor for initializing a ContractAuditEvent with additional context.
     * <p>
     * This constructor is typically used when creating an audit event from a controller
     * or other component where an action message may be provided. For now, it simply sets
     * the action message as an empty string.
     * </p>
     *
     * @param sourceController The controller or source of this event.
     * @param actionMessage    A message describing the action taken, if applicable.
     */
    public ContractAuditEvent(ContractController sourceController, String actionMessage) {
        this.action = actionMessage;  // Set the action message for detailed context
        this.timestamp = System.currentTimeMillis();
        // Additional setup or data from the controller can be added if necessary
    }

    /**
     * Gets the ID of the contract associated with this audit event.
     *
     * @return The contract ID as a string.
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * Gets the type of action performed on the contract.
     *
     * @return The action type (e.g., "CREATE", "UPDATE", "DELETE").
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Gets the user who performed the action on the contract.
     *
     * @return The user's identifier or name.
     */
    public String getUser() {
        return user;
    }

    /**
     * Gets the timestamp of when the action was performed.
     *
     * @return The timestamp in milliseconds since the epoch.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the descriptive action message associated with this audit event.
     * <p>
     * This message can provide additional context about the action that was performed.
     * </p>
     *
     * @return A string describing the action, or an empty string if not set.
     */
    public String getAction() {
        return action;
    }

    // Additional setters for fields (if required) can be added here.
}
