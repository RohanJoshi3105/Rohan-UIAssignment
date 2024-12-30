# Reward Points System

## Overview

This application is designed to manage customer transactions and calculate reward points based on their spending. It provides REST APIs to register customers, log in, manage transactions, and retrieve reward points.

## Features

- Customer registration and authentication
- Adding, updating, retrieving, and deleting transactions
- Calculating and fetching reward points

---

## Entity Models

### Customer
Represents a customer in the system.

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
}
CustomerTransaction
Represents a transaction made by a customer.


@Entity
public class CustomerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
RewardPoints
Tracks the reward points earned by a customer for a specific month and year.


@Entity
public class RewardPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer points;
    private Integer month;
    private Integer year;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
Services
CustomerService
Handles customer registration and authentication.

Key Methods:

registerCustomer(CustomerDTO customerDTO)
authenticate(String email, String password)
CustomerTransactionService
Manages transactions and calculates reward points.

Key Methods:

addTransaction(CustomerTransaction transaction)
updateTransaction(Long transactionId, CustomerTransaction transactionDetails)
getAllTransactionsByCustomerId(Long customerId)
getRewardPointsForCustomer(Long customerId, Integer month, Integer year)
RewardPointsService
Calculates reward points based on transaction amount.

Key Method:

calculateRewardPoints(BigDecimal amount)
Repositories
CustomerRepository: Manages database operations for customers.
CustomerTransactionRepository: Handles database interactions for transactions.
RewardPointsRepository: Fetches reward point records.
Controllers
CustomerController
Endpoints for customer-related operations:

POST /api/customers/register: Register a new customer.
POST /api/customers/login: Log in a customer.
CustomerTransactionController
Endpoints for transaction-related operations:

POST /api/transactions/add: Add a transaction.
GET /api/transactions/customer/{customerId}: Fetch transactions by customer ID.
GET /api/transactions/{transactionId}: Fetch a transaction by ID.
PUT /api/transactions/{transactionId}: Update a transaction.
DELETE /api/transactions/{transactionId}: Delete a transaction.
RewardPointsController
Endpoints for reward points:

GET /api/reward-points/customer/{customerId}: Fetch reward points for a customer.
GET /api/reward-points/all: Fetch all reward points.
Demo API Responses
Customer Registration
Request:

json

POST /api/customers/register
{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "password123"
}
Response:

json

200 OK
"Customer registered successfully"
Customer Login
Request:

json

POST /api/customers/login
{
    "email": "john.doe@example.com",
    "password": "password123"
}
Response:

json

200 OK
"Customer logged in successfully"
Add Transaction
Request:

json

POST /api/transactions/add
{
    "amount": 150.00,
    "date": "2024-12-30",
    "customer": {
        "id": 1
    }
}
Response:

json

200 OK
"Successfully added customer transaction"
Get Reward Points
Request:

json

GET /api/reward-points/customer/1?month=12&year=2024
Response:

json

200 OK
[
    {
        "points": 100,
        "month": 12,
        "year": 2024,
        "customer": {
            "id": 1,
            "name": "John Doe"
        }
    }
]