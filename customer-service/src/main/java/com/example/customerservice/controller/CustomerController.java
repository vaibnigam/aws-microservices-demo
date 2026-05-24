package com.example.customerservice.controller;

import com.example.customerservice.model.Customer;
import com.example.customerservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name="Customer APIs" , description = "Operations related to Customers")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create Customer",
            description = "Creates a new customer profile and persists it in the system"
    )
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }

    @Operation(
            summary = "Get All Customers",
            description = "Retrieves a comprehensive list of all registered customers "
    )
    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @Operation(
            summary = "Get Customer",
            description = "Retrieves specific customer details using the unique customer ID"
    )
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return service.getCustomer(id);
    }
}