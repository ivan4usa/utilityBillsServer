package com.ivan4usa.utilityBills.repositories;

import com.ivan4usa.utilityBills.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
