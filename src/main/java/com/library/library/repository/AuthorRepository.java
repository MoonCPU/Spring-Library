package com.library.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.library.library.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
