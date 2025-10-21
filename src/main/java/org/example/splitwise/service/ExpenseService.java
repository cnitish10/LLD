package org.example.splitwise.service;

import org.example.splitwise.model.Expense;

public class ExpenseService {
    private BalanceManager balanceManager;

    public ExpenseService(BalanceManager balanceManager) {
        this.balanceManager = balanceManager;
    }

    public void addExpense(Expense expense) {
        if (!expense.validate()) {
            System.out.println("Invalid expense data!");
            return;
        }
        balanceManager.updateBalance(expense.getPaidBy(), expense.calculateShare());
    }
}
