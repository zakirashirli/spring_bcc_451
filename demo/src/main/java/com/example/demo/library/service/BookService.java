package com.example.demo.library.service;

import com.example.demo.library.entity.Author;
import com.example.demo.library.entity.Book;
import com.example.demo.library.repository.AuthorRepository;
import com.example.demo.library.repository.BookRepository;
import com.example.demo.restapi.exception.AuthorIsRequiredException;
import com.example.demo.restapi.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found: " + id));

        return book;
    }

    public Book create(Book book) {

        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new AuthorIsRequiredException("Author is required!");
        }

        Long authorId = book.getAuthor().getId();

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found: " + authorId));

        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public Book update(Long id, Book newBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found: " + id));

        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());


        if (newBook.getAuthor() != null && newBook.getAuthor().getId() != null) {
            Long authorId = newBook.getAuthor().getId();

            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new NotFoundException("Author not found: " + authorId));

            authorRepository.save(author);
        }

        return bookRepository.save(book);
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found: " + id));
        bookRepository.delete(book);
    }
}
