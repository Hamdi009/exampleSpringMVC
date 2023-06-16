package com.example.springmvcproject.services;

import java.util.List;
import java.util.Optional;

import com.example.springmvcproject.models.Category;


public interface CategoryService {
    public Optional<Category> getCategory(Long id);
    public Category addCategory(Category c);
    public Category updateCategory(long id, Category c);
    public void deleteCategory(Long id);
    public List<Category> gettAllCategories();
}