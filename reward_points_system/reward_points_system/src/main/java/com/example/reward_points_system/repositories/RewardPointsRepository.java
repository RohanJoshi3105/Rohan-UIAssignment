package com.example.reward_points_system.repositories;

import com.example.reward_points_system.entities.RewardPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
    List<RewardPoints> findByCustomerId(Long customerId);
    List<RewardPoints> findByCustomerIdAndMonthAndYear(Long customerId, Integer month, Integer year);
}
