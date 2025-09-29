package org.example.issueresolutionsystem.model;

import java.util.*;

public class Agent {
    private String id;
    private String email;
    private String name;
    private Set<String> expertise;
    private String currentIssueId;
    private Queue<String> waitlist;
    private List<String> workHistory;

    public Agent(String id, String email, String name, List<String> expertise) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.expertise = new HashSet<>(expertise);
        this.currentIssueId = null;
        this.waitlist = new LinkedList<>();
        this.workHistory = new ArrayList<>();
    }

    public boolean isFree() { return currentIssueId == null; }

    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public Set<String> getExpertise() { return expertise; }
    public String getCurrentIssueId() { return currentIssueId; }
    public Queue<String> getWaitlist() { return waitlist; }
    public List<String> getWorkHistory() { return workHistory; }

    public void setCurrentIssueId(String issueId) { this.currentIssueId = issueId; }

    @Override
    public String toString() {
        return id + " (" + name + ", " + email + ") expertise=" + expertise;
    }
}
