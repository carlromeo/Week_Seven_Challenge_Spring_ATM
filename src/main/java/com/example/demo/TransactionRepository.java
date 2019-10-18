package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TransactionRepository extends CrudRepository { Set<Transaction> findAllByaccountNumber(String accountNumber);
}
