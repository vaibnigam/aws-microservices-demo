package com.example.orderservice.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private List<String> errors;

    public ErrorResponse(String message,
                         LocalDateTime timestamp,
                         List<String> errors) {
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }
}