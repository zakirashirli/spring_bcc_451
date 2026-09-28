package com.example.demo.library.repository;

import com.example.demo.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByPriceGreaterThan(Double price); // price < x
    List<Book> findByPriceLessThan(Double price); // price > x
}
