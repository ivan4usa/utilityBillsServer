package com.ivan4usa.utilityBills.repositories;

import com.ivan4usa.utilityBills.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password=:password WHERE u.id=:id")
    int updatePassword(@Param("password") String password, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email=:email, u.firstName=:firstname, u.lastName=:lastname WHERE u.id=:id")
    User updateUser(
            @Param("email") String email,
            @Param("firstname") String firstname,
            @Param("lastname") String lastname,
            @Param("id") Long id);
}
