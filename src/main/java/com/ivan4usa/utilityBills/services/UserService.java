package com.ivan4usa.utilityBills.services;

import com.ivan4usa.utilityBills.entities.User;
import com.ivan4usa.utilityBills.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return this.repository.findById(id);
    }

    public User add(User user) {
        return repository.save(user);
    }

    public User update(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}