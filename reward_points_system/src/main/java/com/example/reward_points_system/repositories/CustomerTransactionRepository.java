package com.example.reward_points_system.repositories;

import com.example.reward_points_system.entities.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    List<CustomerTransaction> findByCustomerId(Long id);
    boolean existsById(Long id);
}
