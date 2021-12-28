package com.ivan4usa.utilityBills.controllers;

import com.ivan4usa.utilityBills.entities.Account;
import com.ivan4usa.utilityBills.entities.Bill;
import com.ivan4usa.utilityBills.entities.House;
import com.ivan4usa.utilityBills.entities.Payment;
import com.ivan4usa.utilityBills.services.PaymentService;
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
@RequestMapping("/api/payment")
public class PaymentController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final PaymentService service;
    private final UserService userService;

    @Autowired
    public PaymentController(PaymentService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/all")
    public ResponseEntity<?> findAll(@RequestBody Account account) {
        return ResponseEntity.ok(service.findAll(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Payment payment) {
        try {
            if (payment.getId() != null) {payment.setId(null);}
            return ResponseEntity.ok(service.add(payment));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Payment payment) {
        try {
            return ResponseEntity.ok(service.update(payment));
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
            logger.error("Payment was not found when deleting");
            return new ResponseEntity<>("Payment was not found when deleting", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
