package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByOrderId(String id);

    @Query("{orderedDate: {$gt:?0,$lt:?1}}")
    List<Order> getOrdersBetweenDates(Date start, Date end);

    List<Order> findByCustomerId(String id);
}
