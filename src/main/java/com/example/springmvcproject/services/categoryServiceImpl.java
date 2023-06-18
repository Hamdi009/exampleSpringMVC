package com.example.springmvcproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.example.springmvcproject.models.Category;
import com.example.springmvcproject.models.requests.categoryForm;
import com.example.springmvcproject.repositories.CategoryRepository;

@Service
public class categoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    

    @Override
    public Category addCategory(Category c) {
        return categoryRepository.save(c);
    }

    @Override
    public Category updateCategory(Category c) {
        return categoryRepository.save(c);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> gettAllCategories() {
       return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("null"));
    }
    
}