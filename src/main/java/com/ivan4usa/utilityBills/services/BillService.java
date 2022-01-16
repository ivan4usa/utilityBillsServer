package com.ivan4usa.utilityBills.services;

import com.ivan4usa.utilityBills.entities.Bill;
import com.ivan4usa.utilityBills.payloads.BillsResponse;
import com.ivan4usa.utilityBills.payloads.SearchValues;
import com.ivan4usa.utilityBills.repositories.BillRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BillService {

    private final BillRepository repository;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public BillsResponse searchByAccount(SearchValues searchValues) {

        List<Bill> bills = repository.searchByAccount(searchValues.getId(), searchValues.getStartDate(), searchValues.getEndDate());

        LocalDate minDate = repository.getMinDateByAccount(searchValues.getId());
        String minDateString = null;
        if (minDate != null) {
            minDateString = minDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        Bill lastBill = repository.getLastBillByAccount(searchValues.getId());
        return new BillsResponse(bills, minDateString, lastBill);
    }

    public BillsResponse searchByHouse(SearchValues billValues) {
        List<Bill> bills = repository.searchByHouse(billValues.getId(), billValues.getStartDate(), billValues.getEndDate());

        LocalDate minDate = repository.getMinDateByHouse(billValues.getId());
        String minDateString = null;
        if (minDate != null) {
            minDateString = minDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        return new BillsResponse(bills, minDateString, null);
    }

    public List<Bill> findAll(Long accountId) {
        return repository.findAllByAccountId(accountId);
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
    @Transactional
    public Object addMany(List<Bill> bills) {
        return repository.saveAll(bills);
    }
}
