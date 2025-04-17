package com.marufakan;

public class Customer {

    private String name;
    private double existingDebt;
    private double income;

    // Constructor
    public Customer(String name, double existingDebt, double income) {
        this.name = name;
        this.existingDebt = existingDebt;
        this.income = income;
    }

    // Getter ve Setter'lar
    public String getName() {
        return name;
    }

    public double getExistingDebt() {
        return existingDebt;
    }

    public double getIncome() {
        return income;
    }
}
