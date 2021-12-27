package com.ivan4usa.utilityBills.repositories;

import com.ivan4usa.utilityBills.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{

}
