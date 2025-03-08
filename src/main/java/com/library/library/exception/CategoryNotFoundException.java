package com.library.library.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long categoryId) {
        super("The category with id: '" + categoryId + "' does not exist.");
    }

}
