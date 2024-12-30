package com.example.reward_points_system.services;

import com.example.reward_points_system.dto.CustomerDTO;
import com.example.reward_points_system.entities.Customer;
import com.example.reward_points_system.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void registerCustomer(CustomerDTO customerDTO) {

        String hashedPassword = passwordEncoder.encode(customerDTO.getPassword());

        Customer customer = new Customer();

        customer.setName(customerDTO.getName());

        customer.setEmail(customerDTO.getEmail());

        customer.setPassword(hashedPassword);

        customerRepository.save(customer);
    }

    public boolean authenticate(String email, String password) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return passwordEncoder.matches(password, customer.getPassword());
        }
    return false;
    }

}

