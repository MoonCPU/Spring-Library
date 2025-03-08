package com.library.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_category")
    private String bookCategory;

    public Category() {
    }

    public Category(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public Long getId() {
        return this.id;
    }

    public String getBookCategory() {
        return this.bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }
}
