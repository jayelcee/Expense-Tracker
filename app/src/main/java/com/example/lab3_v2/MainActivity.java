package com.example.lab3_v2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText dateEditText, titleEditText, descriptionEditText, amountEditText;
    Spinner categorySpinner;
    String selectedCategory;
    private static final ArrayList<Expense> expenses = new ArrayList<>();
    Button pickDateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateEditText = findViewById(R.id.dateEditText);
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        amountEditText = findViewById(R.id.amountEditText);
        categorySpinner = findViewById(R.id.categorySpinner);
        pickDateButton = findViewById(R.id.pickDateButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        pickDateButton.setOnClickListener(v -> showDatePickerDialog());
    }

    public void submitExpense(View view) {
        String date = dateEditText.getText().toString();
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        double amount = Double.parseDouble(amountEditText.getText().toString());

        Expense expense = new Expense(date, title, description, amount, selectedCategory);
        expenses.add(expense);

        Intent intent = new Intent(MainActivity.this, ExpenseDisplayActivity.class);
        intent.putExtra("EXPENSE_LIST", expenses);
        startActivity(intent);
    }

    public void viewExpenses(View view) {
        Intent intent = new Intent(this, ExpenseDisplayActivity.class);
        intent.putExtra("EXPENSE_LIST", expenses);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearInputs();
    }

    private void clearInputs() {
        dateEditText.setText("");
        titleEditText.setText("");
        descriptionEditText.setText("");
        amountEditText.setText("");
        categorySpinner.setSelection(0);
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String selectedDate = String.format(Locale.US, "%04d-%02d-%02d", year1, month1 + 1, dayOfMonth);
            dateEditText.setText(selectedDate);
        }, year, month, day);

        datePickerDialog.show();
    }

}