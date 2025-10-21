package org.example.splitwise;

import org.example.splitwise.model.EqualExpense;
import org.example.splitwise.model.ExactExpense;
import org.example.splitwise.model.PercentExpense;
import org.example.splitwise.model.User;
import org.example.splitwise.service.BalanceManager;
import org.example.splitwise.service.ExpenseService;

import java.util.Arrays;

public class SplitWiseApp {
    public static void main(String[] args){
        User u1 = new User("u1", "User1", "u1@mail.com", "111");
        User u2 = new User("u2", "User2", "u2@mail.com", "222");
        User u3 = new User("u3", "User3", "u3@mail.com", "333");
        User u4 = new User("u4", "User4", "u4@mail.com", "444");

        BalanceManager balanceManager = new BalanceManager();
        ExpenseService expenseService = new ExpenseService(balanceManager);

        // Run sample inputs
        balanceManager.showBalances();
        balanceManager.showBalanceForUser("u1");

        expenseService.addExpense(new EqualExpense(u1, 1000, Arrays.asList(u1, u2, u3, u4)));
        balanceManager.showBalanceForUser("u3");
        balanceManager.showBalanceForUser("u1");

        expenseService.addExpense(new ExactExpense(u1, 1250, Arrays.asList(u2, u3), Arrays.asList(370.0, 880.0)));
        balanceManager.showBalances();

        expenseService.addExpense(new PercentExpense(u4, 1200, Arrays.asList(u1, u2, u3, u4), Arrays.asList(40.0, 20.0, 20.0, 20.0)));
        balanceManager.showBalanceForUser("u1");
        balanceManager.showBalances();
    }
}
