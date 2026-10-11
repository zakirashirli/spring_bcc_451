package com.example.demo.model.dto.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookRequest {
    private String title;
    private String genre;
    private Double price;
    private Long authorId;

    public BookRequest(String title, String genre, Double price, Long authorId) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.authorId = authorId;
    }
}
