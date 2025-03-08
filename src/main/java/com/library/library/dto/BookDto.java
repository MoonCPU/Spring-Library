package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.library.library.entity.Book;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookDto(String title, int releaseYear, String authorFullName, String category) {
    // constructor for BookDto
    public static BookDto from(Book book, boolean includeAuthor, boolean includeCategory) {

        String authorFullName = includeAuthor ? book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName()
                : null;
        String category = includeCategory ? book.getCategory().getBookCategory() : null;

        return new BookDto(
                book.getTitle(),
                book.getReleaseYear(),
                authorFullName,
                category);
    }
}
