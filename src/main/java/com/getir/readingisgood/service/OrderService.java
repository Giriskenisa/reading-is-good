package com.getir.readingisgood.service;


import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.NewBooksOrder;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public BookRepository bookRepository;

    public synchronized Order createOrder(Order order) throws Exception {

        List<String> serialOfOrderedBooks = getSerialNumbersOfBooks(order);

        List<Book> orderedBooks = bookRepository.findBySerialIn(serialOfOrderedBooks);

        List<Book> stockNotEnough = new ArrayList<>();
        for(Book orderedBook:orderedBooks) {
            int number = getNumber(order, orderedBook);
            if(number>orderedBook.getStock()) {
                stockNotEnough.add(orderedBook);
            }
        }

        if(stockNotEnough.size() >0) {
            throw new Exception("Selected books are out of stock: "+stockNotEnough.toString());
        }else {
            for(Book orderedBook:orderedBooks) {
                orderedBook.setStock(orderedBook.getStock()-getNumber(order, orderedBook));
            }
            bookRepository.saveAll(orderedBooks);
        }
        order.setStatus(Constants.ORDER_CREATED);
        return orderRepository.save(order);
    }



    public synchronized Order cancelOrder(Order prmOrder) throws Exception {
        Order order = orderRepository.findByOrderId(prmOrder.getOrderId());
        List<Book> willUpdate = bookRepository.findBySerialIn(getSerialNumbersOfBooks(order));
        for(Book book : willUpdate) {
            book.setStock(book.getStock()+getNumber(order,book));
        }
        bookRepository.saveAll(willUpdate);
        order.setStatus(Constants.ORDER_CANCELED);
        return orderRepository.save(order);
    }


    public Order getOrderById(String id) throws Exception {
        Order order = orderRepository.findByOrderId(id);
        if(order == null) {
            throw new Exception("There is no Order Recorded By This id: "+id);
        }
        return order;
    }

    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getOrdersBetweenDates(Date start, Date end) throws Exception {
        return orderRepository.getOrdersBetweenDates(start, end);
    }


    /**
     * Getting serialNumbers as list from books attiribute in order
     *
     * @param order order
     * @return List<String>
     */
    private List<String> getSerialNumbersOfBooks(Order order) {
        return order
                .getBooks()
                .stream()
                .map(NewBooksOrder::getSerial)
                .collect(Collectors.toList());
    }

    /**
     * Getting number of ordered book by serialNumber
     *
     * @param order order
     * @param orderedBook orderedBook
     * @return int
     */
    private int getNumber(Order order, Book orderedBook) {
        return order.getBooks()
                .stream()
                .filter(x->x.getSerial().equals(orderedBook.getSerial()))
                .findFirst().get().getNumber();
    }

}
