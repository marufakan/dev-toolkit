package com.example.order;

public class OrderService {

    public double calculateTotal(Order order) {
        if (order == null || order.items() == null || order.items().isEmpty()) {
            throw new IllegalArgumentException("Order is empty or null");
        }

        return order.items().stream()
                .mapToDouble(OrderItem::totalPrice)
                .sum();
    }
}
