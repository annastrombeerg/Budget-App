package com.budgy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VariableExpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.variable);

        Button makeButton = findViewById(R.id.make_button);
        makeButton.setOnClickListener(v -> {
            //Navigera till nästa sida
            Intent intent = new Intent(VariableExpense.this, Summary.class);
            startActivity(intent);
        });
    }
}
