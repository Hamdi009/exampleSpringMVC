package com.example.springmvcproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springmvcproject.models.Category;
import com.example.springmvcproject.models.requests.categoryForm;
import com.example.springmvcproject.services.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class categoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listOfProducts(Model model){
        List<Category> categories = categoryService.gettAllCategories();
        model.addAttribute("categories", categories);       
        return "category/list";
    }

    @GetMapping("/create")
        public String addCategory( Model model){
            model.addAttribute("category", new categoryForm());
            return "category/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("categoryForm") @Valid Category category, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
           // model.addAttribute("categories", categoryService.gettAllCategories());
            return "category/create";
        }
        categoryService.addCategory(category);
        //products.add(product);

        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategory(id);
        model.addAttribute("category", category);
        return "category/update";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id")long id, @ModelAttribute("category") @Valid Category updatedCategory_,BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            return "category/update";
        }
        Category category = categoryService.getCategory(id);
        category.setName(updatedCategory_.getName());
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id")long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

}