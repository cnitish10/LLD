package org.example.issueresolutionsystem.service;

import org.example.issueresolutionsystem.model.Agent;
import org.example.issueresolutionsystem.model.Issue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComplaintResolutionService {
    private Map<String, Issue> issues = new LinkedHashMap<>();
    private Map<String, Agent> agents = new LinkedHashMap<>();
    private int issueCounter = 0;
    private int agentCounter = 0;

    // createIssue
    public String createIssue(String transactionId, String issueType, String subject, String description, String email) {
        issueCounter++;
        String iid = "I" + issueCounter;
        Issue issue = new Issue(iid, transactionId, issueType, subject, description, email);
        issues.put(iid, issue);
        System.out.println(">>> Issue " + iid + " created against transaction \"" + transactionId + "\"");
        return iid;
    }

    // addAgent
    public String addAgent(String agentEmail, String agentName, List<String> expertise) {
        agentCounter++;
        String aid = "A" + agentCounter;
        Agent agent = new Agent(aid, agentEmail, agentName, expertise);
        agents.put(aid, agent);
        System.out.println(">>> Agent " + aid + " created");
        return aid;
    }

    // assignIssue
    public void assignIssue(String issueId) {
        Issue issue = issues.get(issueId);
        if (issue == null) {
            System.out.println(">>> Issue " + issueId + " not found");
            return;
        }
        List<Agent> capableAgents = agents.values().stream()
                .filter(a -> a.getExpertise().contains(issue.getIssueType()))
                .collect(Collectors.toList());

        if (capableAgents.isEmpty()) {
            System.out.println(">>> No agent available for " + issueId);
            return;
        }

        Optional<Agent> freeAgent = capableAgents.stream().filter(Agent::isFree).findFirst();
        if (freeAgent.isPresent()) {
            assignToAgent(freeAgent.get(), issue);
            System.out.println(">>> Issue " + issueId + " assigned to " + freeAgent.get().getId());
        } else {
            Agent chosen = capableAgents.stream().min(Comparator.comparingInt(a -> a.getWaitlist().size())).get();
            chosen.getWaitlist().add(issueId);
            issue.setStatus("Waiting");
            issue.setAssignedAgentId(chosen.getId());
            System.out.println(">>> Issue " + issueId + " added to waitlist of " + chosen.getId());
        }
    }

    private void assignToAgent(Agent agent, Issue issue) {
        agent.setCurrentIssueId(issue.getId());
        agent.getWorkHistory().add(issue.getId());
        issue.setAssignedAgentId(agent.getId());
        issue.setStatus("In Progress");
    }

    // getIssues(filter)
    public List<Issue> getIssues(Map<String, String> filter) {
        Stream<Issue> s = issues.values().stream();
        if (filter.containsKey("email")) {
            return s.filter(i -> i.getEmail().equals(filter.get("email"))).collect(Collectors.toList());
        }
        if (filter.containsKey("type")) {
            return s.filter(i -> i.getIssueType().equals(filter.get("type"))).collect(Collectors.toList());
        }
        if (filter.containsKey("issueId")) {
            Issue i = issues.get(filter.get("issueId"));
            return i != null ? List.of(i) : Collections.emptyList();
        }
        return new ArrayList<>(issues.values());
    }

    // updateIssue
    public void updateIssue(String issueId, String status, String resolution) {
        Issue i = issues.get(issueId);
        if (i == null) return;
        if (status != null) i.setStatus(status);
        if (resolution != null) i.setResolution(resolution);
        System.out.println(">>> " + issueId + " status updated to " + i.getStatus());
    }

    // resolveIssue
    public void resolveIssue(String issueId, String resolution) {
        Issue i = issues.get(issueId);
        if (i == null) return;
        i.setStatus("Resolved");
        i.setResolution(resolution);

        Agent agent = agents.get(i.getAssignedAgentId());
        if (agent != null && issueId.equals(agent.getCurrentIssueId())) {
            agent.setCurrentIssueId(null);
            if (!agent.getWaitlist().isEmpty()) {
                String nextIssueId = agent.getWaitlist().poll();
                Issue next = issues.get(nextIssueId);
                if (next != null) assignToAgent(agent, next);
            }
        }
        System.out.println(">>> " + issueId + " issue resolved");
    }

    // viewAgentsWorkHistory
    public void viewAgentsWorkHistory() {
        System.out.println(">>> Agents Work History:");
        for (Agent a : agents.values()) {
            System.out.println("    " + a.getId() + " -> " + a.getWorkHistory());
        }
    }
}
