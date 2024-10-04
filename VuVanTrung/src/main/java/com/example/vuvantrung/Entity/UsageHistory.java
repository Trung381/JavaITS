package com.example.vuvantrung.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class UsageHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate date;
    private int eUsed;
    private double amount;

    public UsageHistory(){
        this.date = LocalDate.of(2024,1,1);
        this.eUsed = 0;
        this.amount = 0;
    }

    public UsageHistory(LocalDate date, int eUsed, double amount){
//        this.date = LocalDate.parse(date);
        this.date = date;
        this.eUsed = eUsed;
        this.amount = amount;
    }

    public Long getId(){ return this.id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate(){ return this.date; }
    public void setDate(LocalDate date) { this.date = date; }
    public int geteUsed(){ return this.eUsed;}
    public void seteUsed(int eUsed) { this.eUsed = eUsed;}
    public double getAmount(){ return this.amount; }
    public void setAmount(double amount) { this.amount = amount; }

}
