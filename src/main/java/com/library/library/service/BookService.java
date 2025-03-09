package com.library.library.service;

import java.util.List;

import com.library.library.dto.BookDto;
import com.library.library.dto.BookRequest;

public interface BookService {
    BookDto getBook(Long id);

    List<BookDto> getBooks();

    BookDto saveBook(BookRequest bookRequest);

    BookDto updateBook(Long id, BookRequest bookRequest);

    void deleteBook(Long id);

    List<BookDto> getAuthorBooks(Long authorId);

    List<BookDto> getCategoryBooks(Long categoryId);

    List<BookDto> getAuthorCategoryBooks(Long authorId, Long categoryId);
}
