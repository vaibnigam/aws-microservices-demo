package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    // Create Order
    public Order createOrder(Order order) {
        order.setStatus("CREATED");
        return repository.save(order);
    }

    // Get Order by ID
    public Order getOrder(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Get all Orders
    public List<Order> getAllOrders() {
        return repository.findAll();
    }
}