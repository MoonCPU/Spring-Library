package com.library.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.dto.BookDto;
import com.library.library.dto.BookRequest;
import com.library.library.service.BookService;

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
@RequestMapping({ "/book", "/book/" })
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        BookDto bookDto = bookService.getBook(id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> saveBook(@RequestBody BookRequest bookRequest) {
        BookDto savedBook = bookService.saveBook(bookRequest);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        BookDto updatedBook = bookService.updateBook(id, bookRequest);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable Long id) {
        List<BookDto> booksByAuthor = bookService.getAuthorBooks(id);
        return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<BookDto>> getBooksByCategory(@PathVariable Long id) {
        List<BookDto> booksByCategory = bookService.getCategoryBooks(id);
        return new ResponseEntity<>(booksByCategory, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}/category/{categoryId}")
    public ResponseEntity<List<BookDto>> getBooksByAuthorAndCategory(@PathVariable Long authorId,
            @PathVariable Long categoryId) {
        List<BookDto> booksByAuthorAndCategory = bookService.getAuthorCategoryBooks(authorId, categoryId);
        return new ResponseEntity<>(booksByAuthorAndCategory, HttpStatus.OK);
    }

}
