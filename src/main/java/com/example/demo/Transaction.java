package com.example.demo;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int accountNumber;
    private double accountBal;
    private double deposit;

    @ManyToOne()
    @JoinColumn(name="account_id")
    private Account account;



}
