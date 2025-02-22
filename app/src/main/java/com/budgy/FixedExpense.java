package com.budgy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FixedExpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixed);

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            //Navigera till nästa sida
            Intent intent = new Intent(FixedExpense.this, LoanAndCredit.class);
            startActivity(intent);
        });
    }
}
