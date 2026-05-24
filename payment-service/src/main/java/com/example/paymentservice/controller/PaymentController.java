package com.example.paymentservice.controller;

import com.example.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment APIs", description = "Operations related to Payments")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @Operation(
            summary = "Make Order",
            description = "Processes a customer payment based on the provided amount"
    )
    @GetMapping
    public String makePayment(@RequestParam double amount) {
        return service.processPayment(amount);
    }
}