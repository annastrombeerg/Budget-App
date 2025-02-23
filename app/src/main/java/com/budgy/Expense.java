package com.budgy;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private static double income = 0;
    private static final List<Expense> fixedExpenses = new ArrayList<>();
    private static final List<Expense> loanCredits = new ArrayList<>();
    private static final List<Expense> variableExpenses = new ArrayList<>();
    private String name;
    private double amount;


    public Expense(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public static void setIncome(double newIncome) {
        income = newIncome;
    }

    public static double getIncome() {
        return income;
    }

    public static void addFixedExpense(String name, double amount) {
        fixedExpenses.add(new Expense(name, amount));
    }

    public static void addLoanCredit(String name, double amount) {
        loanCredits.add(new Expense(name, amount));
    }

    public static void addVariableExpense(String name, double amount) {
        variableExpenses.add(new Expense(name, amount));
    }

    public static double getTotalFixedExpenses() {
        return fixedExpenses.stream().mapToDouble(e -> e.amount).sum();
    }

    public static double getTotalLoanCredits() {
        return loanCredits.stream().mapToDouble(e -> e.amount).sum();
    }

    public static double getTotalVariableExpenses() {
        return variableExpenses.stream().mapToDouble(e -> e.amount).sum();
    }

    public static void resetData() {
        income = 0;
        fixedExpenses.clear();
        loanCredits.clear();
        variableExpenses.clear();
    }
}
