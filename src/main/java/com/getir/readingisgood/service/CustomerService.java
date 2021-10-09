package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Boolean existEmail(String email) {
        return customerRepository.existsByemail(email);
    }
}
