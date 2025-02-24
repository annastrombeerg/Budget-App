package com.budgy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VariableExpense extends AppCompatActivity implements ExpenseList.OnExpenseDeleteListener{

    EditText expenseName, expenseAmount;
    Button addExpense, makeButton;
    RecyclerView expenseList;
    TextView totalExpense;
    ExpenseList adapter;
    List<Expense> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.variable);

        expenseName = findViewById(R.id.expense_name);
        expenseAmount = findViewById(R.id.expense_amount);
        addExpense = findViewById(R.id.add_expense_button);
        makeButton = findViewById(R.id.make_button);
        expenseList = findViewById(R.id.expense_list);
        totalExpense = findViewById(R.id.total_expense);

        expenses = Expense.getVariableExpenses();
        adapter = new ExpenseList(expenses, this);
        expenseList.setLayoutManager(new LinearLayoutManager(this));
        expenseList.setAdapter(adapter);
        updateTotalExpense();

        addExpense.setOnClickListener(v -> {
            String name = expenseName.getText().toString().trim();
            String amount = expenseAmount.getText().toString().trim();

            if (!name.isEmpty() && !amount.isEmpty()) {
                double addAmount = Double.parseDouble(amount);
                Expense.addFixedExpense(name, addAmount);

                //Uppdatera listan
                expenses.clear();
                expenses.addAll(Expense.getVariableExpenses());
                adapter.notifyDataSetChanged();

                //Rensa inputfält och uppdatera totalsumman
                expenseName.setText("");
                expenseAmount.setText("");
                updateTotalExpense();
            }
        });

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
    }

    @Override
    public void onDeleteExpense(int position) {
        Expense.removeVariableExpense(position);

        //Uppdatera listan efter borttagning
        expenses.clear();
        expenses.addAll(Expense.getVariableExpenses());
        adapter.notifyDataSetChanged();

        updateTotalExpense();
    }

    private void updateTotalExpense() {
        totalExpense.setText("TOTAL: " + Expense.getTotalVariableExpenses() + " KR");
    }
}
