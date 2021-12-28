package com.ivan4usa.utilityBills.services;

import com.ivan4usa.utilityBills.entities.House;
import com.ivan4usa.utilityBills.entities.User;
import com.ivan4usa.utilityBills.repositories.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    private final HouseRepository repository;

    public HouseService(HouseRepository repository) {
        this.repository = repository;
    }

    public List<House> findAll(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public Optional<House> findById(Long id) {
        return repository.findById(id);
    }

    public House add(House house) {
        return repository.save(house);
    }

    public House update(House house) {
        return repository.save(house);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
