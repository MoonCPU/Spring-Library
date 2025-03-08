package com.library.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entity.Author;
import com.library.library.service.AuthorService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping({ "/author", "/author/" })
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        Author author = authorService.getAuthor(id);

        // The diamond operator (<>) allows Java to infer the generic type from context,
        // especially in constructors.
        // whenever you see the diamond <> operator, this is a constructor for a class
        return new ResponseEntity<>(author, HttpStatus.OK);

    }

    @GetMapping({ "/all", "/all/" })
    public ResponseEntity<List<Author>> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.saveAuthor(author), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
