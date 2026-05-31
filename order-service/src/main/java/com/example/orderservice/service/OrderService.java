package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.OrderResponseDTO;

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
    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {

        String customerResponse = restTemplate.getForObject(
                "http://customer-container:8083/customers/" + requestDTO.getCustomerId(),
                String.class
        );
        String paymentResponse = restTemplate.getForObject(
                "http://payment-container:8082/payment?amount=" + requestDTO.getPrice(),
                String.class
        );

        Order order = new Order();

        order.setCustomerId(requestDTO.getCustomerId());
        order.setProductName(requestDTO.getProductName());
        order.setQuantity(requestDTO.getQuantity());
        order.setPrice(requestDTO.getPrice());

        order.setStatus(OrderStatus.CREATED);

        order.setStatus(OrderStatus.PAYMENT_PENDING);

        if ("SUCCESS".equalsIgnoreCase(paymentResponse)) {
            order.setStatus(OrderStatus.PAID);
        } else {
            order.setStatus(OrderStatus.FAILED);
        }

        Order savedOrder = repository.save(order);

        return new OrderResponseDTO(
                savedOrder.getId(),
                paymentResponse
        );
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