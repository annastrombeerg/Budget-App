package com.budgy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;

public class Income extends AppCompatActivity {

    Button nextButton;
    EditText income, incomeAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income);

        nextButton = findViewById(R.id.next_button);
        income = findViewById(R.id.income_amount);

        if (Expense.getIncome() > 0) {
            incomeAmount.setText(String.valueOf(Expense.getIncome()));
        }

        nextButton.setOnClickListener(v -> {
            double income = 0;
            if (!incomeAmount.getText().toString().isEmpty()) {
                income = Double.parseDouble(incomeAmount.getText().toString());
            }
            Expense.setIncome(income);
            //Navigera till nästa sida
            Intent intent = new Intent(Income.this, FixedExpense.class);
            startActivity(intent);
        });

        income.addTextChangedListener(new TextWatcher() {
            private boolean isEditing = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (isEditing) return;
                isEditing = true;
                String text = editable.toString().replace(" KR", "").trim();
                if (!text.isEmpty()) {
                    text += " KR";
                    income.setText(text);
                    income.setSelection(text.length() - 3);
                }
                isEditing = false;
            }
        });
    }
}
