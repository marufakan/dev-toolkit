package com.example.order;

public record OrderItem(String name, int quantity, double unitPrice) {
    public double totalPrice() {
        return quantity * unitPrice;
    }
}
