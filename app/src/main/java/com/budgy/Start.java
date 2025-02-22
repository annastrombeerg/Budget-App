package com.budgy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(v -> {
            //Navigera vidare till nästa
            Intent intent = new Intent(Start.this, Income.class);
            startActivity(intent);
        });
    }
}
