package com.library.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.entity.Category;
import com.library.library.exception.CategoryNotFoundException;
import com.library.library.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(Long id) {
        return findCategoryById(id);
    }

    @Override
    public List<Category> getCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category updatedCategory = findCategoryById(id);

        updatedCategory.setBookCategory(category.getBookCategory());
        return categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        findCategoryById(id);
        categoryRepository.deleteById(id);
    }

    // helper function
    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
