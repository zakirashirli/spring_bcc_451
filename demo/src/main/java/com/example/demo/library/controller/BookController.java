package com.example.demo.library.controller;

import com.example.demo.model.dto.book.BookRequest;
import com.example.demo.model.dto.book.BookResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping("/books/create")
    public BookResponse createBook(@RequestBody BookRequest bookRequest) {
        return bookService.create(bookRequest);
    }

    @PutMapping("/books/update/{id}")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        return bookService.update(id,bookRequest);
    }

    @DeleteMapping("/books/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    // Query Methods
    @GetMapping("/search/{title}")
    public List<Book> getBooksByTitle(@RequestParam String title) {
        return bookService.getByTitle(title);
    }

    @GetMapping("/search/{genre}")
    public List<Book> getBooksByGenre(@RequestParam String genre) {
        return bookService.getByGenre(genre);
    }

    @GetMapping("/price/greater")
    public List<Book> getBooksByMinPrice(@RequestParam Double price) {
        return bookService.getByMinPrice(price);
    }

    @GetMapping("/price/less")
    public List<Book> getBooksByMaxPrice(@RequestParam Double price) {
        return bookService.getByMaxPrice(price);
    }
}
