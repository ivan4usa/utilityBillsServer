package com.ivan4usa.utilityBills.controllers;

import com.ivan4usa.utilityBills.entities.Account;
import com.ivan4usa.utilityBills.entities.House;
import com.ivan4usa.utilityBills.services.AccountService;
import com.ivan4usa.utilityBills.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@CrossOrigin
@RestController
@EnableWebMvc
@RequestMapping("/api/account")
public class AccountController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final AccountService service;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/all")
    public ResponseEntity<?> findAll(@RequestBody Long userId) {
        return ResponseEntity.ok(service.findAll(userId));
    }

    @PostMapping("/all-by-house")
    public ResponseEntity<?> findAllByHouse(@RequestBody House house) {
        return ResponseEntity.ok(service.findAllByHouse(house));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Account account) {
        try {
            if (account.getId() != null) {account.setId(null);}
            return ResponseEntity.ok(service.add(account));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(service.update(account));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Account was not found when deleting");
            return new ResponseEntity<>("Account was not found when deleting", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
