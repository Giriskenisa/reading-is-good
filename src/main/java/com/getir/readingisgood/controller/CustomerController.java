package com.getir.readingisgood.controller;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody final Customer customer) {
        if(service.existEmail(customer.getEmail())) {
            return ResponseEntity.status(409).body("Email Already In Use");
        }
        Customer saved = service.addCustomer(customer);
        if(saved != null) {
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.internalServerError().build();
    }


}
