package com.example.springmvcproject.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Optional;

import com.example.springmvcproject.models.Category;
import com.example.springmvcproject.models.Product;
import com.example.springmvcproject.models.requests.ProductForm;
import com.example.springmvcproject.services.CategoryService;
import com.example.springmvcproject.services.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String listOfProducts(Model model){
        List<Product> products = productService.gettAllProduct();
        model.addAttribute("products", products);       
        return "list";
    }

    @GetMapping("/create")
        public String addProduct( Model model){
            model.addAttribute("product", new ProductForm());
            model.addAttribute("categories", categoryService.gettAllCategories());
            return "create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("productForm") @Valid Product productForm, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.gettAllCategories());
            return "create";
        }
        productService.addProduct(productForm);
        //products.add(product);

        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model){
        Product product = productService.getProduct(id);
        List<Category> categories = categoryService.gettAllCategories();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id")long id, @ModelAttribute("product") @Valid Product updateProduct_,BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            return "update";
        }
        Product product = productService.getProduct(id);
        

        product.setCode(updateProduct_.getCode());
        product.setName(updateProduct_.getName());
        product.setPrice(updateProduct_.getPrice());
        product.setQuantity(updateProduct_.getQuantity());
        product.setCategory(updateProduct_.getCategory());
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id")long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

   
    // @PostMapping(value="{id}/update")
    // public String updateProduct(@PathVariable("id") long id, @Valid @ModelAttribute("product") ProductForm product, BindingResult bindingResult){
    //     if (bindingResult.hasErrors()) return "update";
    //     Product exisitingProduct = null;
    //     try {
    //         exisitingProduct = products.stream().filter(x -> (long) id == x.getId() ).findFirst().get();

    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         return "redirect:/products";
    //     }
    //     if (exisitingProduct == null) return "redirect:/products";
    //     exisitingProduct.setName(product.getName());
    //     exisitingProduct.setCode(product.getCode());
    //     exisitingProduct.setPrice(product.getPrice());
    //     exisitingProduct.setQuantity(product.getQuantity());
    //     return "redirect:/products";
    // }


    // @GetMapping("/{id}/delete")
    // public String deleteItem(@PathVariable("id") Long id){
    //     Product productToDelete = null;
        
    //     try {
    //        productToDelete =  products.stream().filter(x -> (long) id == x.getId() ).findFirst().get();
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         return "redirect:/products";
    //     }
    //     if (productToDelete == null) return "redirect:/products";
    //     products.remove(productToDelete);
    //     return "redirect:/products";
    // }

}