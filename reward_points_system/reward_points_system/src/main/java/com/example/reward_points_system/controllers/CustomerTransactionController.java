package com.example.reward_points_system.controllers;

import com.example.reward_points_system.entities.CustomerTransaction;
import com.example.reward_points_system.services.CustomerTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/transactions")
public class CustomerTransactionController {
    @Autowired
    private CustomerTransactionService customerTransactionService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerTransactionController.class);

    //Add a new transaction
    @PostMapping("/add")
    public ResponseEntity<String> addTransaction(@RequestBody CustomerTransaction customerTransaction) {
        logger.info("Received request to add customer transaction: {}");
        customerTransactionService.addTransaction(customerTransaction);
        return new ResponseEntity<>("Successfully added customer transaction", HttpStatus.OK);
    }

    // Get all transactions by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerTransaction>> getTransactionsByCustomerId(@PathVariable Long customerId) {
        logger.info("Received request to get customer trensaction with ID: {}");
        List<CustomerTransaction> transactions = customerTransactionService.getAllTransactionsByCustomerId(customerId);
        if (transactions.isEmpty()) {
            return notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(transactions); // 200 OK with the reward points
    }

    // Get transaction by transaction ID
    @GetMapping("/{transactionId}")
    public ResponseEntity<CustomerTransaction> getTransactionById(@PathVariable Long transactionId) {
        logger.info("Received request to get customer trensaction with ID: {}");
        Optional<CustomerTransaction> transaction = customerTransactionService.getTransactionById(transactionId);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> notFound().build());
    }

    // Update transaction
    @PutMapping("/{transactionId}")
    public ResponseEntity<String> updateTransaction(
            @PathVariable Long transactionId, @RequestBody CustomerTransaction transactionDetails) {
        boolean transactionExists = customerTransactionService.transactionExists(transactionId); // Check existence
        if (!transactionExists) {
            return ResponseEntity.notFound().build(); // Return 404 if transaction not found
        }
        // Proceed with updating the transaction if it exists
        customerTransactionService.updateTransaction(transactionId, transactionDetails);
        return ResponseEntity.ok("Successfully updated customer transaction");
    }

    //Delete Transaction
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long trasactionId) {
        customerTransactionService.deleteTransaction(trasactionId);
        return ResponseEntity.ok("Successfully deleted customer transaction");
    }
}

