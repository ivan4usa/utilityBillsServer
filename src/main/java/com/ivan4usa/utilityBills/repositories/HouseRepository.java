package com.ivan4usa.utilityBills.repositories;

import com.ivan4usa.utilityBills.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
