package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Transaction, Long> { Account findById(long id);
    Account findByAcctno(String accountNumber);
}
