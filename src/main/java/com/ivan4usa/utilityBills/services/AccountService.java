package com.ivan4usa.utilityBills.services;

import com.ivan4usa.utilityBills.entities.Account;
import com.ivan4usa.utilityBills.entities.House;
import com.ivan4usa.utilityBills.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> findAll(House house) {
        return repository.findAllByHouse(house);
    }

    public Optional<Account> findById(Long id) {
        return repository.findById(id);
    }

    public Account add(Account account) {
        return repository.save(account);
    }

    public Account update(Account account) {
        return repository.save(account);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
