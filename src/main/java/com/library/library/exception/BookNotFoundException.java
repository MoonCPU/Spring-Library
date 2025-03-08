package com.library.library.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long bookId) {
        super("The book at index '" + bookId + "'does not exist.");
    }
}
