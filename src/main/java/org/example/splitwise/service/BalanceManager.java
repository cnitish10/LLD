package org.example.splitwise.service;

import org.example.splitwise.model.User;
import java.util.*;

public class BalanceManager {
    private Map<String, Map<String, Double>> balances = new HashMap<>();

    public void updateBalance(User paidBy, Map<User, Double> shares) {
        for (Map.Entry<User, Double> entry : shares.entrySet()) {
            User participant = entry.getKey();
            double amount = entry.getValue();

            // participant owes paidBy
            balances.putIfAbsent(participant.getUserId(), new HashMap<>());
            balances.putIfAbsent(paidBy.getUserId(), new HashMap<>());

            double oldDebt = balances.get(participant.getUserId())
                    .getOrDefault(paidBy.getUserId(), 0.0);
            double oldReverse = balances.get(paidBy.getUserId())
                    .getOrDefault(participant.getUserId(), 0.0);

            double net = oldDebt - oldReverse + amount;
            if (net > 0) {
                balances.get(participant.getUserId()).put(paidBy.getUserId(), net);
                balances.get(paidBy.getUserId()).remove(participant.getUserId());
            } else if (net < 0) {
                balances.get(paidBy.getUserId()).put(participant.getUserId(), -net);
                balances.get(participant.getUserId()).remove(paidBy.getUserId());
            } else {
                balances.get(participant.getUserId()).remove(paidBy.getUserId());
                balances.get(paidBy.getUserId()).remove(participant.getUserId());
            }
        }
    }

    public void showBalances() {
        boolean empty = true;
        for (String u1 : balances.keySet()) {
            for (Map.Entry<String, Double> entry : balances.get(u1).entrySet()) {
                if (entry.getValue() > 0.0) {
                    System.out.println(u1 + " owes " + entry.getKey() + ": " + String.format("%.2f", entry.getValue()));
                    empty = false;
                }
            }
        }
        if (empty) System.out.println("No balances");
    }

    public void showBalanceForUser(String userId) {
        boolean empty = true;
        if (balances.containsKey(userId)) {
            for (Map.Entry<String, Double> entry : balances.get(userId).entrySet()) {
                if (entry.getValue() > 0.0) {
                    System.out.println(userId + " owes " + entry.getKey() + ": " + String.format("%.2f", entry.getValue()));
                    empty = false;
                }
            }
        }
        for (String u1 : balances.keySet()) {
            if (balances.get(u1).containsKey(userId)) {
                double val = balances.get(u1).get(userId);
                if (val > 0.0) {
                    System.out.println(u1 + " owes " + userId + ": " + String.format("%.2f", val));
                    empty = false;
                }
            }
        }
        if (empty) System.out.println("No balances");
    }
}
