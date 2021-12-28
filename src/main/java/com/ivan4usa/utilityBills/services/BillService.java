package com.ivan4usa.utilityBills.services;

import com.ivan4usa.utilityBills.entities.Account;
import com.ivan4usa.utilityBills.entities.Bill;
import com.ivan4usa.utilityBills.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository repository;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public List<Bill> findAll(Account account) {
        return repository.findAllByAccount(account);
    }

    public Optional<Bill> findById(Long id) {
        return repository.findById(id);
    }

    public Bill add(Bill bill) {
        return repository.save(bill);
    }

    public Bill update(Bill bill) {
        return repository.save(bill);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
