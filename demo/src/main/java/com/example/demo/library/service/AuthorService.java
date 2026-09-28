package com.example.demo.library.service;

import com.example.demo.library.entity.Author;
import com.example.demo.library.repository.AuthorRepository;
import com.example.demo.restapi.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author is not found: " + id));

        return author;
    }

    public Author create(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Long id, Author newAuthor) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author is not found: " + id));

        author.setFullname(newAuthor.getFullname());
        author.setCountry(newAuthor.getCountry());

        return authorRepository.save(author);
    }

    public void delete(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author is not found: " + id));
        authorRepository.delete(author);
    }
}
