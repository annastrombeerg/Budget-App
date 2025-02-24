package com.budgy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class VariableExpense extends AppCompatActivity {

    EditText expenseName, expenseAmount;
    Button addExpense, makeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.variable);

        expenseName = findViewById(R.id.expense_name);
        expenseAmount = findViewById(R.id.expense_amount);
        addExpense = findViewById(R.id.add_expense_button);
        makeButton = findViewById(R.id.make_button);

        makeButton.setOnClickListener(v -> {
            double totalIncome = Expense.getIncome();
            double totalFixed = Expense.getTotalFixedExpenses();
            double totalLoan = Expense.getTotalLoanCredits();
            double totalVariable = Expense.getTotalVariableExpenses();
            double totalBudget = totalIncome - (totalFixed + totalLoan + totalVariable);
            Expense.setIncome(totalBudget);
            //Navigera till nästa sida
            Intent intent = new Intent(VariableExpense.this, Summary.class);
            startActivity(intent);
        });

        addExpense.setOnClickListener(v -> {
            if (!expenseName.getText().toString().isEmpty() && !expenseAmount.getText().toString().isEmpty()) {
                Expense.addVariableExpense(expenseName.getText().toString(), Double.parseDouble(expenseAmount.getText().toString()));
                expenseName.setText("");
                expenseAmount.setText("");
            }
        });
    }
}
