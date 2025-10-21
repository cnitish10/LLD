package org.example.splitwise.model;

import java.util.*;

public class PercentExpense implements Expense {
    private User paidBy;
    private double amount;
    private List<User> participants;
    private List<Double> percents;

    public PercentExpense(User paidBy, double amount, List<User> participants, List<Double> percents) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.participants = participants;
        this.percents = percents;
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
            double share = (percents.get(i) / 100.0) * amount;
            if (!participants.get(i).equals(paidBy)) {
                shareMap.put(participants.get(i), share);
            }
        }
        return shareMap;
    }

    @Override
    public boolean validate() {
        double sum = 0;
        for (double p : percents) sum += p;
        return Math.abs(sum - 100.0) < 0.01;
    }
}
