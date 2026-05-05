package com.example.paymentservice.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String processPayment(double amount) {

        // simulate success/failure
        if (amount > 10000) {
            return "FAILED";
        }

        return "SUCCESS";
    }
}