package com.ivan4usa.utilityBills.controllers;

import com.ivan4usa.utilityBills.entities.Account;
import com.ivan4usa.utilityBills.entities.Bill;
import com.ivan4usa.utilityBills.services.BillService;
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
@RequestMapping("/api/bill")
public class BillController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final BillService service;
    private final UserService userService;

    @Autowired
    public BillController(BillService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Bill bill) {
        try {
            if (bill.getId() != null) {bill.setId(null);}
            return ResponseEntity.ok(service.add(bill));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Bill bill) {
        try {
            return ResponseEntity.ok(service.update(bill));
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
            logger.error("Bill was not found when deleting");
            return new ResponseEntity<>("Bill was not found when deleting", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
