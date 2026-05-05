package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    // Create Order with Payment Integration
    public Order createOrder(Order order) {

        // Call Payment Service
        String paymentUrl = "http://localhost:8082/payment?amount=" + order.getPrice();

        String paymentResponse = restTemplate.postForObject(paymentUrl, null, String.class);

        // Update status based on payment response
        if ("SUCCESS".equals(paymentResponse)) {
            order.setStatus("COMPLETED");
        } else {
            order.setStatus("FAILED");
        }

        return repository.save(order);
    }

    // Get Order by ID
    public Order getOrder(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Get All Orders
    public List<Order> getAllOrders() {
        return repository.findAll();
    }
}