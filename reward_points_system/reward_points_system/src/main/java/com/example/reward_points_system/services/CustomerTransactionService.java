package com.example.reward_points_system.services;

import com.example.reward_points_system.entities.CustomerTransaction;
import com.example.reward_points_system.entities.RewardPoints;
import com.example.reward_points_system.repositories.CustomerRepository;
import com.example.reward_points_system.repositories.CustomerTransactionRepository;
import com.example.reward_points_system.repositories.RewardPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerTransactionService {
    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    @Autowired
    private RewardPointsRepository rewardPointsRepository;

    @Autowired
    private RewardPointsService rewardPointsService;

    public void addTransaction(CustomerTransaction transaction) {
        customerTransactionRepository.save(transaction);

        // Calculate and save reward points
        int rewardPoints = rewardPointsService.calculateRewardPoints(transaction.getAmount());
        RewardPoints reward = new RewardPoints();
        reward.setCustomer(transaction.getCustomer());
        reward.setPoints(rewardPoints);
        reward.setMonth(transaction.getDate().getMonthValue());
        reward.setYear(transaction.getDate().getYear());
        rewardPointsRepository.save(reward);
    }
    // Get transaction by ID
    public Optional<CustomerTransaction> getTransactionById(Long transactionId) {
        return customerTransactionRepository.findById(transactionId);

    }

    // Get all transactions by customer ID
    public List<CustomerTransaction> getAllTransactionsByCustomerId(Long customerId) {
        return customerTransactionRepository.findByCustomerId(customerId);
    }

    // Edit transaction (update transaction)
    public void updateTransaction(Long transactionId, CustomerTransaction transactionDetails) {
        Optional<CustomerTransaction> transaction = customerTransactionRepository.findById(transactionId);
        if (transaction.isPresent()) {
            CustomerTransaction updatedTransaction = transaction.get();
            updatedTransaction.setAmount(transactionDetails.getAmount());
            updatedTransaction.setDate(transactionDetails.getDate());
            customerTransactionRepository.save(updatedTransaction);
        }
    }

    // Delete transaction
    public void deleteTransaction(Long transactionId) {
        customerTransactionRepository.deleteById(transactionId);
    }

    public List<RewardPoints> getRewardPointsForCustomer(Long customerId, Integer month, Integer year) {
        return rewardPointsRepository.findByCustomerIdAndMonthAndYear(customerId, month, year);
    }

    public boolean transactionExists(Long transactionId) {
        return customerTransactionRepository.existsById(transactionId);
    }
}
