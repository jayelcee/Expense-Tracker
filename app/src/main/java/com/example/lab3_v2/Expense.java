package com.example.lab3_v2;

import androidx.annotation.NonNull;
import java.text.DecimalFormat;
import java.io.Serializable;

public class Expense implements Serializable {
    private String date;
    private String title;
    private String description;
    private double amount;
    private String category;

    public Expense(String date, String title, String description, double amount, String category) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String formattedAmount = decimalFormat.format(amount);
        return "Date: " + date +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nAmount: Php " + formattedAmount +
                "\nCategory: " + category;
    }
}