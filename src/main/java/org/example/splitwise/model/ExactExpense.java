package org.example.splitwise.model;

import java.util.*;

public class ExactExpense implements Expense {
    private User paidBy;
    private double amount;
    private List<User> participants;
    private List<Double> shares;

    public ExactExpense(User paidBy, double amount, List<User> participants, List<Double> shares) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.participants = participants;
        this.shares = shares;
    }

    @Override
    public User getPaidBy() { return paidBy; }
    @Override
    public double getAmount() { return amount; }
    @Override
    public List<User> getParticipants() { return participants; }

    @Override
    public Map<User, Double> calculateShare() {
        Map<User, Double> shareMap = new HashMap<>();
        for (int i = 0; i < participants.size(); i++) {
            if (!participants.get(i).equals(paidBy)) {
                shareMap.put(participants.get(i), shares.get(i));
            }
        }
        return shareMap;
    }

    @Override
    public boolean validate() {
        double sum = 0;
        for (double s : shares) sum += s;
        return Math.abs(sum - amount) < 0.01;
    }
}
