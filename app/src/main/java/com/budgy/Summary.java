package com.budgy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {
    Button startOver, income, expenses;
    PieChart pieChart;
    TextView balanceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);

        pieChart = findViewById(R.id.piechart);
        setupPieChart(pieChart);

        startOver = findViewById(R.id.start_over_button);
        income = findViewById(R.id.income_button);
        expenses = findViewById(R.id.expenses_button);
        balanceView = findViewById(R.id.balance);

        double balance = Expense.getIncome() - (Expense.getTotalFixedExpenses() + Expense.getTotalLoanCredits() + Expense.getTotalVariableExpenses());
        balanceView.setText(balance + " KR");

        startOver.setOnClickListener(v -> {
            Expense.resetData();
            startActivity(new Intent(Summary.this, Start.class));
        });
        income.setOnClickListener(v -> startActivity(new Intent(Summary.this, Income.class)));
        expenses.setOnClickListener(v -> startActivity(new Intent(Summary.this, FixedExpense.class)));
    }

    private void setupPieChart(PieChart pieChart) {
        double totalIncome = Expense.getIncome();
        double totalFixed = Expense.getTotalFixedExpenses();
        double totalLoan = Expense.getTotalLoanCredits();
        double totalVariable = Expense.getTotalVariableExpenses();

        pieChart.clearChart();

        if (totalIncome > 0)
            pieChart.addPieSlice(new PieModel("Income", (float) totalIncome, Color.parseColor("#B8E1FF")));
        if (totalFixed > 0)
            pieChart.addPieSlice(new PieModel("Fixed", (float) totalFixed, Color.parseColor("#087E8B")));
        if (totalLoan > 0)
            pieChart.addPieSlice(new PieModel("Loan/Credit", (float) totalLoan, Color.parseColor("#FFF275")));
        if (totalVariable > 0)
            pieChart.addPieSlice(new PieModel("Variable", (float) totalVariable, Color.parseColor("#C492B1")));

        pieChart.startAnimation();
    }

}
