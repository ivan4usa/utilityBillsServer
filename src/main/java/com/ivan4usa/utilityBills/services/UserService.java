package com.ivan4usa.utilityBills.services;

import com.ivan4usa.utilityBills.entities.User;
import com.ivan4usa.utilityBills.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User add(User user) {
        return repository.save(user);
    }

    public int updatePassword(String encodedPassword, Long userId) {
        return repository.updatePassword(encodedPassword, userId);
    }

    public Object updateUser(String email, String firstname, String lastname, Long userId) {
        return repository.updateUser(email, firstname, lastname, userId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}