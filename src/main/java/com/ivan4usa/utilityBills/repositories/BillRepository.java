package com.ivan4usa.utilityBills.repositories;

import com.ivan4usa.utilityBills.entities.Account;
import com.ivan4usa.utilityBills.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{

    List<Bill> findAllByAccount(Account account);
}
