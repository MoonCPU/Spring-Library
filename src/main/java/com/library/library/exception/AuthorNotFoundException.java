package com.library.library.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long authorId) {
        super("The author with id: '" + authorId + "' does not exist in our records");
    }

}
