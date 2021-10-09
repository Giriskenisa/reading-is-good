package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.repository.BookRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @SneakyThrows
    public Book addBook(Book book) {
        return repository.save(book);
    }

    @SneakyThrows
    public List<Book> addBooks(List<Book> books) {
        return repository.saveAll(books);
    }

    @SneakyThrows
    public Book updateStockOfBook(Book book) {
        Book book1 = repository.findByserial(book.getSerial());
        if(book1 == null) {
            throw new Exception("Book Not Found");
        }
        book1.setStock(book.getStock());
        return repository.save(book1);
    }

    public Book getBySerial(String serial) {
        return repository.findByserial(serial);
    }

    public Boolean existsBySerial(String serial) {
        return repository.existsByserial(serial);
    }
}
