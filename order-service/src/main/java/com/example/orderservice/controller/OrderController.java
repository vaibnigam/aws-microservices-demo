package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order APIs", description = "Operations related to Orders")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Create Order",
            description = "Creates a new order after customer validation and payment processing"
    )
    @PostMapping
    public OrderResponseDTO createOrder(@Valid @RequestBody OrderRequestDTO requestDTO) {
        return orderService.createOrder(requestDTO);
    }

    @Operation(
            summary = "Get All Orders",
            description = "Fetches all orders from database"
    )
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}