package com.example.reward_points_system.controllers;

import com.example.reward_points_system.dto.CustomerDTO;
import com.example.reward_points_system.entities.Customer;
import com.example.reward_points_system.services.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        logger.info("Received request to register customer: {}");
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestParam String email, @RequestParam String password) {
        logger.info("Received request to login customer: {}");
        boolean isAuthenticated = customerService.authenticate(email, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Customer logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }

    }

}
