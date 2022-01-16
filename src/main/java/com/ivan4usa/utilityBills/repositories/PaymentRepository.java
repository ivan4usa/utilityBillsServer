package com.ivan4usa.utilityBills.repositories;

import com.ivan4usa.utilityBills.entities.Bill;
import com.ivan4usa.utilityBills.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p from Payment p WHERE p.account.id=:accountId")
    List<Payment> findAllByAccount(@Param("accountId") Long accountId);

    @Query("SELECT p FROM Payment p WHERE p.account.id=:accountId " +
            "AND ( p.payDate BETWEEN cast(:startDate as timestamp) AND cast(:endDate as timestamp)) " +
            "ORDER BY p.payDate DESC")
    List<Payment> searchByAccount(@Param("accountId") Long accountId,
                               @Param("startDate") LocalDate startDate,
                               @Param("endDate") LocalDate endDate);

    @Query("SELECT min(p.payDate) From Payment p WHERE p.account.id=:accountId")
    LocalDate getMinDateByAccount(@Param("accountId") Long accountId);

    @Query("SELECT p FROM Payment p WHERE p.payDate = (SELECT MAX(p.payDate) FROM p WHERE p.account.id=:accountId) AND p.account.id=:accountId")
    List<Payment> getLastPaymentByAccount(@Param("accountId") Long accountId);
}
