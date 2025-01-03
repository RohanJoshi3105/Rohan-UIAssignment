package com.example.reward_points_system.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RewardPointsService {
    public int calculateRewardPoints(BigDecimal amount) {
        int points = 0;
        // Points for amount over $100
        if (amount.compareTo(new BigDecimal("100")) > 0) {
            BigDecimal over100 = amount.subtract(new BigDecimal("100"));
            points += over100.intValue() * 2; // 2 points for every dollar over $100
            amount = new BigDecimal("100");
        }
        // Points for amount between $50 and $100
        if (amount.compareTo(new BigDecimal("50")) > 0) {
            BigDecimal between50and100 = amount.subtract(new BigDecimal("50"));
            points += between50and100.intValue(); // 1 point for every dollar between $50 and $100
        }
        return points;
    }
}
