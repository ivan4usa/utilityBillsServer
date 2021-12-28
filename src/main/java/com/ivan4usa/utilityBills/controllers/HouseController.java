package com.ivan4usa.utilityBills.controllers;

import com.ivan4usa.utilityBills.entities.House;
import com.ivan4usa.utilityBills.entities.User;
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
        return ResponseEntity.ok(service.findAll(userId));
    }
}
