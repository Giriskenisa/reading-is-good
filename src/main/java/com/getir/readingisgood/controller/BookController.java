package com.getir.readingisgood.controller;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.service.BookService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;


    @PostMapping("/add")
    @SneakyThrows
    public ResponseEntity<?> addBook(@Valid @RequestBody final Book book) {
        Book exist = service.getBySerial(book.getSerial());

        ResponseEntity<String> validBook = validBook(book);
        if (validBook != null) return validBook;

        Book saved = service.addBook(book);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/add/list")
    @SneakyThrows
    public ResponseEntity<?> addBookList(@RequestBody final List<Book> books) {
        for (Book book: books
             ) {
            ResponseEntity<String> book1 = validBook(book);
            if (book1 != null) return book1;
        }
        List<Book> saved = service.addBooks(books);
       return ResponseEntity.ok(saved);
    }



    @PutMapping("/update-stock/{serialNo}")
    public ResponseEntity<?> updateStock(@PathVariable("serialNo") String serial,
                                         @RequestParam("stock") int stock) {
        Book willUpdate = service.getBySerial(serial);

        if(stock < 0) {
            return ResponseEntity.badRequest().body("Please Enter a Valid Stock Number");
        }

        if(willUpdate != null) {
            willUpdate.setStock(stock);
            service.updateStockOfBook(willUpdate);
            return ResponseEntity.ok(willUpdate);
        }
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<String> validBook(Book book) {
        if(service.existsBySerial(book.getSerial())) {
            return ResponseEntity.status(409).body("Book Already Exist By Serial "+ book.getSerial());
        }
        if(book.getStock()<0) {
            return ResponseEntity.badRequest().body("Please Enter a Valid Stock Number of book: "+ book.getTitle());
        }
        return null;
    }

}
