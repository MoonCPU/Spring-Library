package com.library.library.service;

import java.util.List;

import com.library.library.entity.Category;

public interface CategoryService {
    Category getCategory(Long id);

    List<Category> getCategories();

    Category saveCategory(Category category);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}
