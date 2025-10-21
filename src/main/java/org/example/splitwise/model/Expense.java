package org.example.splitwise.model;


import java.util.List;
import java.util.Map;

public interface Expense {
    User getPaidBy();
    double getAmount();
    List<User> getParticipants();
    Map<User, Double> calculateShare();
    boolean validate();
}
