package com.example.vuvantrung.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDate;

//@Entity
//public class UsageHistory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private LocalDate date;
//    private int eUsed;
//    private double amount;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonBackReference // Ngăn chặn vòng lặp vô hạn khi chuyển đổi sang JSON
//    private User user;
//
//    public UsageHistory(){
//        this.date = LocalDate.of(2024,1,1);
//        this.eUsed = 0;
//        this.amount = 0;
//    }
//
//    public UsageHistory(LocalDate date, int eUsed, double amount, User user){
////        this.date = LocalDate.parse(date);
//        this.date = date;
//        this.eUsed = eUsed;
//        this.amount = amount;
//        this.user = user;
//    }
//
//    public Long getId(){ return this.id; }
//    public void setId(Long id) { this.id = id; }
//    public LocalDate getDate(){ return this.date; }
//    public void setDate(LocalDate date) { this.date = date; }
//    public int geteUsed(){ return this.eUsed;}
//    public void seteUsed(int eUsed) { this.eUsed = eUsed;}
//    public double getAmount(){ return this.amount; }
//    public void setAmount(double amount) { this.amount = amount; }
//    public void setUser(User user) { this.user = user;}
//    public User getUser() {return this.user;}
//}
@Entity
public class UsageHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int eUsed;
    private double amount;
    private int status;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonBackReference // Ngăn chặn vòng lặp vô hạn khi chuyển đổi sang JSON
    private User user;

    public UsageHistory() {
        this.date = LocalDate.of(2024, 1, 1);
        this.eUsed = 0;
        this.amount = 0;
    }

    public UsageHistory(LocalDate date, int eUsed, double amount, User user, int status) {
        this.date = date;
        this.eUsed = eUsed;
        this.amount = amount;
        this.user = user;
        this.status = status;
    }

    // Getters and Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int geteUsed() {
        return this.eUsed;
    }

    public void seteUsed(int eUsed) {
        this.eUsed = eUsed;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus(){
        return this.status;
    }

    public void setStatus(int status){
        this.status = status;
    }
}
