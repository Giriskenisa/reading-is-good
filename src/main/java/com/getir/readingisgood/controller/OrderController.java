package com.getir.readingisgood.controller;


import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.service.OrderService;
import com.getir.readingisgood.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;


    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody final Order order) throws Exception {
        Order savedOrder = service.createOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable("customerId") final String customerId) {
        List<Order> orders = service.getOrdersByCustomerId(customerId);
        if (orders.isEmpty()) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/between-date")
    public ResponseEntity<List<Order>> getBetweenDate(@RequestParam("start") @DateTimeFormat(pattern = Constants.DATE_FORMAT) Date start,
                                                      @RequestParam("end") @DateTimeFormat(pattern = Constants.DATE_FORMAT)  Date end) throws Exception {
        List<Order> orders = service.getOrdersBetweenDates(start, end);
        if(orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") final String orderId) throws Exception {
        Order order = service.getOrderById(orderId);
        if(order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

}
