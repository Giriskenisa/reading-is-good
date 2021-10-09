package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, Long> {

    public boolean existsByemail(String email);
}
