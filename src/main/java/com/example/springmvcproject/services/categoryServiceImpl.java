package com.example.springmvcproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvcproject.models.Category;
import com.example.springmvcproject.repositories.CategoryRepository;

@Service
public class categoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category addCategory(Category c) {
        return categoryRepository.save(c);
    }

    @Override
    public Category updateCategory(long id, Category c) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category exisitingCategory = optionalCategory.get();
            exisitingCategory.setName(c.getName());
            

            return categoryRepository.save(exisitingCategory);
        }
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> gettAllCategories() {
       return categoryRepository.findAll();
    }
    
}