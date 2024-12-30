package com.example.reward_points_system.services;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardPointsServiceTest {

    private final RewardPointsService rewardPointsService = new RewardPointsService();

    @Test
    public void testCalculateRewardPoints_Above100() {
        BigDecimal amount = new BigDecimal("120");
        int expectedPoints = 90; // (2 * 20) + (1 * 50) = 40 + 50 = 90
        int actualPoints = rewardPointsService.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCalculateRewardPoints_Exactly100() {
        BigDecimal amount = new BigDecimal("100");
        int expectedPoints = 50; // (1 * 50) = 50 points for amounts between 50 and 100
        int actualPoints = rewardPointsService.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCalculateRewardPoints_Between50And100() {
        BigDecimal amount = new BigDecimal("75");
        int expectedPoints = 25; // (1 * 25) = 25 points for amounts between 50 and 100
        int actualPoints = rewardPointsService.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCalculateRewardPoints_Below50() {
        BigDecimal amount = new BigDecimal("40");
        int expectedPoints = 0; // No points for amounts below 50
        int actualPoints = rewardPointsService.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCalculateRewardPoints_Over100() {
        BigDecimal amount = new BigDecimal("150");
        int expectedPoints = 150; // (2 * 50) + (1 * 50) = 100 + 50 = 150 points
        int actualPoints = rewardPointsService.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }
}
