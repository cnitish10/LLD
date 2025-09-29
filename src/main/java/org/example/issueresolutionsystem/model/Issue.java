package org.example.issueresolutionsystem.model;

public class Issue {
    private String id;
    private String transactionId;
    private String issueType;
    private String subject;
    private String description;
    private String email;
    private String status;
    private String resolution;
    private String assignedAgentId;

    public Issue(String id, String transactionId, String issueType, String subject, String description, String email) {
        this.id = id;
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.status = "Open";
        this.resolution = null;
        this.assignedAgentId = null;
    }

    // Getters & setters
    public String getId() { return id; }
    public String getTransactionId() { return transactionId; }
    public String getIssueType() { return issueType; }
    public String getSubject() { return subject; }
    public String getDescription() { return description; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }
    public String getResolution() { return resolution; }
    public String getAssignedAgentId() { return assignedAgentId; }

    public void setStatus(String status) { this.status = status; }
    public void setResolution(String resolution) { this.resolution = resolution; }
    public void setAssignedAgentId(String assignedAgentId) { this.assignedAgentId = assignedAgentId; }

    @Override
    public String toString() {
        return id + " {\"" + transactionId + "\", \"" + issueType + "\", \"" + subject + "\", \"" +
                description + "\", \"" + email + "\", \"" + status + "\"" +
                (resolution != null ? ", resolution: \"" + resolution + "\"" : "") +
                (assignedAgentId != null ? ", assignedTo: " + assignedAgentId : "") +
                "}";
    }
}
