package com.ivan4usa.utilityBills.services;

import com.ivan4usa.utilityBills.entities.Payment;
import com.ivan4usa.utilityBills.payloads.BillsResponse;
import com.ivan4usa.utilityBills.payloads.PaymentsResponse;
import com.ivan4usa.utilityBills.payloads.SearchValues;
import com.ivan4usa.utilityBills.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<Payment> findAll(Long accountId) {
        return repository.findAllByAccount(accountId);
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

    public PaymentsResponse searchByAccount(SearchValues searchValues) {
        List<Payment> payments = repository.searchByAccount(searchValues.getId(), searchValues.getStartDate(), searchValues.getEndDate());

        LocalDate minDate = repository.getMinDateByAccount(searchValues.getId());
        String minDateString = null;
        if (minDate != null) {
            minDateString = minDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        Payment lastPayment = repository.getLastPaymentByAccount(searchValues.getId()).get(0);
        return new PaymentsResponse(payments, minDateString, lastPayment);
    }
}
