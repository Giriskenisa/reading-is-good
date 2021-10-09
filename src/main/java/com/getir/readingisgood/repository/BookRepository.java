package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface BookRepository extends MongoRepository<Book, String> {

    Book findByserial(String serial);

    Boolean existsByserial(String serial);

    List<Book> findBySerialIn(List<String> serials);

}
