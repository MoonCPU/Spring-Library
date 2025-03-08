package com.library.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.library.library.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByAuthorId(Long authorId);

    List<Book> findByCategoryId(Long categoryId);

    List<Book> findByAuthorIdAndCategoryId(Long authorId, Long categoryId);

}
