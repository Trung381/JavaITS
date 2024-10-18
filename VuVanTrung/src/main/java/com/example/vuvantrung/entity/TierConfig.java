package com.example.vuvantrung.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TierConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int minUsage;
    private int maxUsage;
    private double price;

    public Long getId(){ return this.id; }

    public void setId(Long id){ this.id = id; }

    public int getMinUsage(){ return this.minUsage; }

    public void setMinUsage(int minUsage){ this.minUsage = minUsage; }

    public int getMaxUsage(){ return this.maxUsage; }

    public void setMaxUsage(int maxUsage){ this.maxUsage = maxUsage; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }
}
