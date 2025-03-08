package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

// This annotation makes sure that only fields with values are included in the JSON response, and it skips fields that are null.
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookRequest(String title, int releaseYear, Long authorId, Long categoryId) {
}