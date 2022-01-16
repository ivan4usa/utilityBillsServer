package com.ivan4usa.utilityBills.controllers;

import com.ivan4usa.utilityBills.entities.House;
import com.ivan4usa.utilityBills.payloads.StatisticsRequest;
import com.ivan4usa.utilityBills.services.HouseService;
import com.ivan4usa.utilityBills.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Controller
@CrossOrigin
@RestController
@EnableWebMvc
@RequestMapping("/api/house")
public class HouseController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final HouseService service;
    private final UserService userService;

    @Autowired
    public HouseController(HouseService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/all")
    public ResponseEntity<?> findAll(@RequestBody Long userId) {
        try {
            return ResponseEntity.ok(service.findAll(userId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody House house) {
        try {
            return ResponseEntity.ok(service.add(house));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody House house) {
        try {
            return ResponseEntity.ok(service.update(house));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/search-statistics")
    public ResponseEntity<?> searchStatistics(@RequestBody StatisticsRequest statisticsRequest) {
        try {
            return ResponseEntity.ok(service.searchStatistics(statisticsRequest));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
