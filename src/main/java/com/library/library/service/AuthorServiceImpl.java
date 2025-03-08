package com.library.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.entity.Author;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.repository.AuthorRepository;

//we dont't need to use Optional anymore because we are already handling nulls with exception throwing
//which is why we don't use the get() method to unwrap Optionals anymore

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthor(Long id) {
        return findAuthorById(id);
    }

    @Override
    public List<Author> getAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author updatedAuthor = findAuthorById(id);

        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setLastName(author.getLastName());

        return authorRepository.save(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        findAuthorById(id);
        authorRepository.deleteById(id);
    }

    // helper function
    private Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

}
