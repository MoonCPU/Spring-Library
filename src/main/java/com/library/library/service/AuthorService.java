package com.library.library.service;

import java.util.List;

import com.library.library.entity.Author;

public interface AuthorService {
    Author getAuthor(Long id);

    List<Author> getAuthors();

    Author saveAuthor(Author author);

    Author updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);
}
