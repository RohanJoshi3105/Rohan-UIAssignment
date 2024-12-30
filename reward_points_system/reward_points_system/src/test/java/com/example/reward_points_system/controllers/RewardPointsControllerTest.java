package com.example.reward_points_system.controllers;

import com.example.reward_points_system.entities.RewardPoints;
import com.example.reward_points_system.repositories.RewardPointsRepository;
import com.example.reward_points_system.services.CustomerTransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // This will enable Mockito annotations
public class RewardPointsControllerTest {

    @Mock
    private CustomerTransactionService customerTransactionService;

    @Mock
    private RewardPointsRepository rewardPointsRepository;

    @InjectMocks
    private RewardPointsController rewardPointsController; // Automatically inject mocks into the controller

    private RewardPoints rewardPoints1;
    private RewardPoints rewardPoints2;

    @BeforeEach
    void setUp() {
        // Initialize the reward points entities for testing
        rewardPoints1 = new RewardPoints(1L, 120, 12, 2024, null);
        rewardPoints2 = new RewardPoints(2L, 150, 12, 2024, null);
    }

    @Test
    void testGetRewardReport_whenPointsFound() {
        //Mocking the service to return some reward points
        Long customerId = 1L;
        Integer month = 12;
        Integer year = 2024;
        List<RewardPoints> rewardPointsList = Arrays.asList(rewardPoints1, rewardPoints2);

        when(customerTransactionService.getRewardPointsForCustomer(customerId, month, year)).thenReturn(rewardPointsList);

        // When: Calling the controller method
        ResponseEntity<List<RewardPoints>> response = rewardPointsController.getRewardReport(customerId, month, year);

        // Then: Verifying the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetRewardReport_whenPointsNotFound() {
        // Mocking the service to return an empty list
        Long customerId = 1L;
        Integer month = 12;
        Integer year = 2024;
        List<RewardPoints> rewardPointsList = Arrays.asList();

        when(customerTransactionService.getRewardPointsForCustomer(customerId, month, year)).thenReturn(rewardPointsList);

        // When: Calling the controller method
        ResponseEntity<List<RewardPoints>> response = rewardPointsController.getRewardReport(customerId, month, year);

        // Then: Verifying the response
        assertEquals(404, response.getStatusCodeValue()); // 404 when no data found
    }

    @Test
    void testGetAllRewardReports_whenPointsFound() {
        // Mocking the repository to return some reward points
        List<RewardPoints> rewardPointsList = Arrays.asList(rewardPoints1, rewardPoints2);

        when(rewardPointsRepository.findAll()).thenReturn(rewardPointsList);

        // When: Calling the controller method
        ResponseEntity<List<RewardPoints>> response = rewardPointsController.getAllRewardReports();

        // Then: Verifying the response
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetAllRewardReports_whenPointsNotFound() {
        // Mocking the repository to return an empty list
        List<RewardPoints> rewardPointsList = Arrays.asList();

        when(rewardPointsRepository.findAll()).thenReturn(rewardPointsList);

        // When: Calling the controller method
        ResponseEntity<List<RewardPoints>> response = rewardPointsController.getAllRewardReports();

        // Then: Verifying the response
        assertEquals(404, response.getStatusCodeValue()); // 404 when no data found
    }
}
