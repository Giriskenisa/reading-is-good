package com.getir.readingisgood.service;


import com.getir.readingisgood.dto.Statistic;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private OrderRepository orderRepository;

    public Map<String, Statistic> getStatistic(String customerId, String year) {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        Map<String, Statistic> statisticsMap = new HashMap<>();

        if(orders.isEmpty()) {
            return new HashMap<>();
        }else {
            orders = orders.stream()
                    .filter(x->year.equals(yearFormat.format(x.getOrderedDate())))
                    .collect(Collectors.toList());
        }

        for(Order order:orders) {
            String month = monthFormat.format(order.getOrderedDate());
             Statistic monthlyStatistic = statisticsMap.get(month);
            if(monthlyStatistic == null) {
                monthlyStatistic = new Statistic(month, 1, order.getTotalBooks(), order.getTotalAmount());
            } else {
                monthlyStatistic.setTotalBook(monthlyStatistic.getTotalOrder()+1);
                monthlyStatistic.setTotalAmount(monthlyStatistic.getTotalAmount()+order.getTotalAmount());
                monthlyStatistic.setTotalBook(monthlyStatistic.getTotalBook()+order.getTotalBooks());
            }
            statisticsMap.put(month,monthlyStatistic);
        }
        return statisticsMap;
    }
}
