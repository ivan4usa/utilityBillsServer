package com.ivan4usa.utilityBills.services;

import com.ivan4usa.utilityBills.entities.Account;
import com.ivan4usa.utilityBills.entities.Payment;
import com.ivan4usa.utilityBills.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<Payment> findAll(Account account) {
        return repository.findAllByAccount(account);
    }

    public Optional<Payment> findById(Long id) {
        return repository.findById(id);
    }

    public Payment add(Payment payment) {
        return repository.save(payment);
    }

    public Payment update(Payment payment) {
        return repository.save(payment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
