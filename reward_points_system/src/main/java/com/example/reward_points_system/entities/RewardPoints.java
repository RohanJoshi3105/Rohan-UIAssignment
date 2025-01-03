package com.example.reward_points_system.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public RewardPoints() {
    }

    public RewardPoints(Long id, Integer points, Integer month, Integer year, Customer customer) {
        this.id = id;
        this.points = points;
        this.month = month;
        this.year = year;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "RewardPoints{" +
                "id=" + id +
                ", points=" + points +
                ", month=" + month +
                ", year=" + year +
                ", customer=" + customer +
                '}';
    }
}
