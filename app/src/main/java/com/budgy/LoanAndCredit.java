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

public class LoanAndCredit extends AppCompatActivity implements ExpenseList.OnExpenseDeleteListener{
    EditText expenseName, expenseAmount;
    Button addExpense, nextButton;
    RecyclerView expenseList;
    TextView totalExpense;
    ExpenseList adapter;
    List<Expense> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loanandcredit);

        expenseName = findViewById(R.id.expense_name);
        expenseAmount = findViewById(R.id.expense_amount);
        addExpense = findViewById(R.id.add_expense_button);
        nextButton = findViewById(R.id.next_button);
        expenseList = findViewById(R.id.expense_list);
        totalExpense = findViewById(R.id.total_expense);

        expenses = Expense.getLoanCreditExpenses();
        adapter = new ExpenseList(expenses, this);
        expenseList.setLayoutManager(new LinearLayoutManager(this));
        expenseList.setAdapter(adapter);
        updateTotalExpense();

        addExpense.setOnClickListener(v -> {
            String name = expenseName.getText().toString().trim();
            String amount = expenseAmount.getText().toString().trim();

            if (!name.isEmpty() && !amount.isEmpty()) {
                double addAmount = Double.parseDouble(amount);
                Expense.addLoanCredit(name, addAmount);

                //Uppdatera listan
                expenses.clear();
                expenses.addAll(Expense.getLoanCreditExpenses());
                adapter.notifyDataSetChanged();

                //Rensa inputfält och uppdatera totalsumman
                expenseName.setText("");
                expenseAmount.setText("");
                updateTotalExpense();
            }
        });

        nextButton.setOnClickListener(v -> {
            expenses.clear(); //Rensar RecyclerView-listan
            adapter.notifyDataSetChanged(); //Uppdaterar listan i UI
            //Navigera till nästa sida
            Intent intent = new Intent(LoanAndCredit.this, VariableExpense.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDeleteExpense(int position) {
        Expense.removeLoanCreditExpense(position);

        // Uppdatera listan efter borttagning
        expenses.clear();
        expenses.addAll(Expense.getLoanCreditExpenses());
        adapter.notifyDataSetChanged();

        updateTotalExpense();
    }

    private void updateTotalExpense() {
        totalExpense.setText("TOTAL: " + Expense.getTotalLoanCredits() + " KR");
    }
}
