package com.example.lab3_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import android.util.TypedValue;

public class ExpenseDisplayActivity extends AppCompatActivity {
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_display);

        tableLayout = findViewById(R.id.tableLayout);

        if (getIntent().hasExtra("EXPENSE_LIST")) {
            ArrayList<Expense> expenses = (ArrayList<Expense>) getIntent().getSerializableExtra("EXPENSE_LIST");

            assert expenses != null;
            for (Expense expense : expenses) {
                addDataRow(expense);
            }
        }
    }

    public void goBack(View view) {
        finish();
    }

    private void addDataRow(Expense expense) {
        TableRow row = new TableRow(this);

        // Create TextView elements and set text
        TextView dateTextView = createTextViewWithMargin(expense.getDate(), 6);
        TextView titleTextView = createTextViewWithMargin(expense.getTitle(), 6);
        TextView descriptionTextView = createTextViewWithMargin(expense.getDescription(), 6);
        TextView amountTextView = createTextViewWithMargin("Php " + formatAmount(expense.getAmount()), 6);
        TextView categoryTextView = createTextViewWithMargin(expense.getCategory(), 6);

        // Add TextView elements to the row
        row.addView(dateTextView);
        row.addView(titleTextView);
        row.addView(descriptionTextView);
        row.addView(amountTextView);
        row.addView(categoryTextView);

        // Add the row to the TableLayout
        tableLayout.addView(row);
    }

    private TextView createTextViewWithMargin(String text, int leftMarginDp) {
        TextView textView = createTextView(text);

        // Convert dp to pixels
        int leftMarginPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftMarginDp, getResources().getDisplayMetrics());

        // Set the left margin
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(leftMarginPixels, 0, 0, 0);
        textView.setLayoutParams(params);

        return textView;
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        return textView;
    }

    private String formatAmount(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(amount);
    }
}