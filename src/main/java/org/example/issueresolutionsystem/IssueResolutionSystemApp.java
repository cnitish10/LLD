package org.example.issueresolutionsystem;

import org.example.issueresolutionsystem.service.ComplaintResolutionService;

import java.util.Arrays;
import java.util.Map;

public class IssueResolutionSystemApp {
    public static void main(String args[]){
        ComplaintResolutionService service = new ComplaintResolutionService();

        String i1 = service.createIssue("T1", "Payment Related", "Payment Failed", "Money debited", "testUser1@test.com");
        String i2 = service.createIssue("T2", "Mutual Fund Related", "Purchase Failed", "Unable to purchase", "testUser2@test.com");
        String i3 = service.createIssue("T3", "Payment Related", "Payment Failed", "Money debited", "testUser2@test.com");

        service.addAgent("agent1@test.com", "Agent 1", Arrays.asList("Payment Related", "Gold Related"));
        service.addAgent("agent2@test.com", "Agent 2", Arrays.asList("Mutual Fund Related", "Payment Related"));

        service.assignIssue(i1);
        service.assignIssue(i2);
        service.assignIssue(i3);

        System.out.println("\n>>> Issues by email testUser2@test.com:");
        service.getIssues(Map.of("email", "testUser2@test.com")).forEach(System.out::println);

        System.out.println("\n>>> Issues by type Payment Related:");
        service.getIssues(Map.of("type", "Payment Related")).forEach(System.out::println);

        service.updateIssue(i3, "In Progress", "Waiting for confirmation");
        service.resolveIssue(i3, "Amount will be reversed");

        System.out.println();
        service.viewAgentsWorkHistory();
    }
}
