package com.budgy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpenseList extends RecyclerView.Adapter<ExpenseList.ExpenseViewHolder> {

    private List<Expense> expenseList;
    private OnExpenseDeleteListener deleteListener;

    public ExpenseList(List<Expense> expenseList, OnExpenseDeleteListener deleteListener) {
        this.expenseList = expenseList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_list, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.expenseName.setText(expense.getName());
        holder.expenseAmount.setText(expense.getAmount() + " KR");

        holder.deleteButton.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteExpense(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView expenseName, expenseAmount;
        ImageButton deleteButton;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseName = itemView.findViewById(R.id.expense_name);
            expenseAmount = itemView.findViewById(R.id.expense_amount);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

    public interface OnExpenseDeleteListener {
        void onDeleteExpense(int position);
    }
}
