package com.example.demo.library.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.dto.book.BookRequest;
import com.example.demo.model.dto.book.BookResponse;
import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
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

    // CRUD methods
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found: " + id));

        return book;
    }

    public BookResponse create(BookRequest bookRequest) {

        if (bookRequest.getAuthorId() == null) {
            throw new BadRequestException("Author is required!");
        }

        Long authorId = bookRequest.getAuthorId();

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found: " + authorId));


        Book book = new Book();

        book.setTitle(bookRequest.getTitle());
        book.setGenre(bookRequest.getGenre());
        book.setPrice(bookRequest.getPrice());
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);

        return convertToResponse(savedBook);
    }

    public BookResponse update(Long id, BookRequest newBook) {

        Long authorId = newBook.getAuthorId();

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found: " + authorId));

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found: " + id));

        book.setTitle(newBook.getTitle());
        book.setGenre(newBook.getGenre());
        book.setPrice(newBook.getPrice());
        book.setAuthor(author);


        Book updatedBook = bookRepository.save(book);

        return convertToResponse(updatedBook);
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found: " + id));
        bookRepository.delete(book);
    }

    // QUERY METHODS
    public List<Book> getByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> getByGenre(String genre){
        return bookRepository.findByGenreContainingIgnoreCase(genre);
    }

    public List<Book> getByMinPrice(Double minPrice){
        return bookRepository.findByPriceGreaterThan(minPrice);
    }

    public List<Book> getByMaxPrice(Double maxPrice){
        return bookRepository.findByPriceLessThan(maxPrice);
    }

    // DTO -> Entity
    private BookResponse convertToResponse(Book book){
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getPrice(),
                book.getAuthor().getFullname()
        );
    }
}
