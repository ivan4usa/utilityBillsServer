package com.ivan4usa.utilityBills.repositories;
import com.ivan4usa.utilityBills.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{

    @Query("SELECT b FROM Bill b WHERE b.account.id=:accountId " +
            "AND ( b.dateStart BETWEEN cast(:startDate as timestamp) AND cast(:endDate as timestamp) " +
            "OR b.dateEnd BETWEEN cast(:startDate as timestamp) AND cast(:endDate as timestamp) " +
            "OR cast(:startDate as timestamp) BETWEEN b.dateStart AND b.dateEnd  ) ORDER BY b.dateEnd DESC")
    List<Bill> searchByAccount(@Param("accountId") Long accountId,
                      @Param("startDate") LocalDate startDate,
                      @Param("endDate") LocalDate endDate);

    @Query("SELECT b FROM Bill b WHERE b.account.id=:accountId " +
            "AND b.dateEnd BETWEEN cast(:startDate as timestamp) AND cast(:endDate as timestamp) " +
            "ORDER BY b.dateEnd DESC")
    List<Bill> searchByEndDate(@Param("accountId") Long accountId,
                               @Param("startDate") LocalDate startDate,
                               @Param("endDate") LocalDate endDate);

    @Query("SELECT b FROM Bill b " +
            "LEFT JOIN Account a ON b.account.id = a.id "+
            "LEFT JOIN House h ON a.houseId = h.id " +
            "WHERE h.id=:houseId " +
            "AND ( b.dateStart BETWEEN cast(:startDate as timestamp) AND cast(:endDate as timestamp) " +
            "OR b.dateEnd BETWEEN cast(:startDate as timestamp) AND cast(:endDate as timestamp) " +
            "OR cast(:startDate as timestamp) BETWEEN b.dateStart AND b.dateEnd  ) ORDER BY b.dateEnd DESC")
    List<Bill> searchByHouse(@Param("houseId") Long houseId,
                               @Param("startDate") LocalDate startDate,
                               @Param("endDate") LocalDate endDate);

    @Query("Select b from Bill b where b.account.id=:accountId")
    List<Bill> findAllByAccountId(@Param("accountId") Long accountId);


    @Query("SELECT min(b.dateStart) FROM Bill b " +
            "LEFT JOIN Account a ON b.account.id = a.id " +
            "LEFT JOIN House h ON a.houseId = h.id " +
            "WHERE h.id=:houseId")
    LocalDate getMinDateByHouse(@Param("houseId") Long houseId);

    @Query("SELECT min(b.dateStart) From Bill b WHERE b.account.id=:accountId")
    LocalDate getMinDateByAccount(@Param("accountId") Long accountId);

    @Query("SELECT b FROM Bill b WHERE b.dateEnd = (SELECT MAX(b.dateEnd) FROM b WHERE b.account.id=:accountId) AND b.account.id=:accountId")
    Bill getLastBillByAccount(@Param("accountId") Long accountId);
}
