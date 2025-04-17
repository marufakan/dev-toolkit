package com.example.order;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private final OrderService service = new OrderService();

    @Test
    void shouldCalculateTotalCorrectly() {
        Order order = new Order(List.of(
                new OrderItem("Book", 2, 15.0),
                new OrderItem("Pen", 3, 2.5)
        ));

        double total = service.calculateTotal(order);
        assertEquals(15.0 * 2 + 2.5 * 3, total);
    }

    @Test
    void shouldThrowExceptionWhenOrderIsEmpty() {
        Order order = new Order(List.of());
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateTotal(order);
        });
    }

    @Test
    void shouldThrowExceptionWhenOrderIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateTotal(null);
        });
    }
}