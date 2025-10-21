package org.example.splitwise.model;

import java.util.*;

public class EqualExpense implements Expense {
    private User paidBy;
    private double amount;
    private List<User> participants;

    public EqualExpense(User paidBy, double amount, List<User> participants) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.participants = participants;
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
        int n = participants.size();
        double base = Math.floor((amount * 100.0 / n)) / 100.0;
        double remainder = Math.round(amount * 100.0) / 100.0 - (base * n);

        for (int i = 0; i < n; i++) {
            double share = base;
            if (i == 0) share += remainder; // adjust rounding difference
            if (!participants.get(i).equals(paidBy)) {
                shareMap.put(participants.get(i), share);
            }
        }
        return shareMap;
    }

    @Override
    public boolean validate() { return true; }
}
