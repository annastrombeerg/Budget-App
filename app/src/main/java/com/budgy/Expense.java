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

    public static List<Expense> getFixedExpenses() {
        return new ArrayList<>(fixedExpenses);
    }

    public static List<Expense> getLoanCreditExpenses() {
        return new ArrayList<>(fixedExpenses);
    }

    public static List<Expense> getVariableExpenses() {
        return new ArrayList<>(fixedExpenses);
    }

    public static void resetData() {
        income = 0;
        fixedExpenses.clear();
        loanCredits.clear();
        variableExpenses.clear();
    }

    public static void removeFixedExpense(int position) {
        if (position >= 0 && position < fixedExpenses.size()) {
            fixedExpenses.remove(position);
        }
    }

    public static void removeLoanCreditExpense(int position) {
        if (position >= 0 && position < loanCredits.size()) {
            loanCredits.remove(position);
        }
    }

    public static void removeVariableExpense(int position) {
        if (position >= 0 && position < variableExpenses.size()) {
            variableExpenses.remove(position);
        }
    }

}
