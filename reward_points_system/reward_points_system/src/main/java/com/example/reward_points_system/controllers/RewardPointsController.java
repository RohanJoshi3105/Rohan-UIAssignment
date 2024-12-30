package com.example.reward_points_system.controllers;

import com.example.reward_points_system.entities.RewardPoints;
import com.example.reward_points_system.repositories.RewardPointsRepository;
import com.example.reward_points_system.services.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/reward-points")
public class RewardPointsController {
    @Autowired
    private CustomerTransactionService customerTransactionService;

    @Autowired
    private RewardPointsRepository rewardPointsRepository;

    private static final Logger logger = LoggerFactory.getLogger(RewardPointsController.class);

    // Get reward points for a customer (by customer ID, month, and year)
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<RewardPoints>> getRewardReport(
            @PathVariable Long customerId, @RequestParam Integer month, @RequestParam Integer year) {
        logger.info("Received request to get customer with ID: {}");
        List<RewardPoints> rewardPoints = customerTransactionService.getRewardPointsForCustomer(customerId, month, year);
        if (rewardPoints.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(rewardPoints); // 200 OK with the reward points
    }

    // Get all reward points for all customers
    @GetMapping("/all")
    public ResponseEntity<List<RewardPoints>> getAllRewardReports() {
        logger.info("Received request to get all customer: {}");
        List<RewardPoints> rewardPoints = rewardPointsRepository.findAll();
        if (rewardPoints.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(rewardPoints); // 200 OK with the reward points
    }
}
