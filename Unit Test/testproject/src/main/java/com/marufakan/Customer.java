package com.marufakan;

public class Customer {
    private String name;
    private String identityNumber;

    public Customer(String name, String identityNumber) {
        this.name = name;
        this.identityNumber = identityNumber;
    }

    public String getName() {
        return name;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }
}

