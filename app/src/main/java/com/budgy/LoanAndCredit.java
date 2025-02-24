package com.budgy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoanAndCredit extends AppCompatActivity {
    EditText expenseName, expenseAmount;
    Button addExpense, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loanandcredit);

        expenseName = findViewById(R.id.expense_name);
        expenseAmount = findViewById(R.id.expense_amount);
        addExpense = findViewById(R.id.add_expense_button);
        nextButton = findViewById(R.id.next_button);

        nextButton.setOnClickListener(v -> {
            //Navigera till nästa sida
            Intent intent = new Intent(LoanAndCredit.this, VariableExpense.class);
            startActivity(intent);
        });

        addExpense.setOnClickListener(v -> {
            if (!expenseName.getText().toString().isEmpty() && !expenseAmount.getText().toString().isEmpty()) {
                Expense.addLoanCredit(expenseName.getText().toString(), Double.parseDouble(expenseAmount.getText().toString()));
                expenseName.setText("");
                expenseAmount.setText("");
            }
        });
    }
}
