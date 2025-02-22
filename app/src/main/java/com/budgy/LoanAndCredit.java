package com.budgy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoanAndCredit extends AppCompatActivity {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loanandcredit);

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            //Navigera till nästa sida
            Intent intent = new Intent(LoanAndCredit.this, VariableExpense.class);
            startActivity(intent);
        });
    }
}
