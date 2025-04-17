package com.example.order;

import java.util.List;

public record Order(List<OrderItem> items) {}