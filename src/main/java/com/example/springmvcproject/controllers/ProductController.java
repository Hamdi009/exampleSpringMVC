package com.example.springmvcproject.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import com.example.springmvcproject.models.Product;
import com.example.springmvcproject.models.requests.ProductForm;
import com.example.springmvcproject.services.ProductService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    // private static List<Product> products = new ArrayList<>();
    // private static Long idCount = 0L;
    // static {
    //     products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9", 500D, 50, "samsung-s9.png"));
    //     products.add(new Product(++idCount, "NK-5P", "Nokia Plus", 60D, 60, "no-image.png"));
    //     products.add(new Product(++idCount, "IP-7", "Iphone 7", 550D, 30, "iphone-7.png"));
    // }

    @GetMapping()
    //@RequestMapping("/")
    public String listOfProducts(Model model){
        List<Product> products = productService.gettAllProduct();
        model.addAttribute("products", products);       
        return "list";
    }

    @GetMapping(value = "create")
        public String addProduct(Model model){
            model.addAttribute("product", new ProductForm());
            return "create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("productForm") @Valid Product productForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "create";
        Product createdProduct = productService.addProduct(productForm);
        products.add(product);

        return "redirect:/products";
    }

    @GetMapping("/{id}/update")
    public String showEditForm(@PathVariable("id") long id, Model model){
        Product exisitingProduct = null;
        try {
            exisitingProduct = products.stream().filter(x -> (long) id == x.getId()).findFirst().get();
        } catch (Exception e) {
            return "redirect:/products";
        }
        model.addAttribute("product", exisitingProduct);
        return "update";
    }

    @PostMapping(value="{id}/update")
    public String updateProduct(@PathVariable("id") long id, @Valid @ModelAttribute("product") ProductForm product, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "update";
        Product exisitingProduct = null;
        try {
            exisitingProduct = products.stream().filter(x -> (long) id == x.getId() ).findFirst().get();

        } catch (Exception e) {
            // TODO: handle exception
            return "redirect:/products";
        }
        if (exisitingProduct == null) return "redirect:/products";
        exisitingProduct.setName(product.getName());
        exisitingProduct.setCode(product.getCode());
        exisitingProduct.setPrice(product.getPrice());
        exisitingProduct.setQuantity(product.getQuantity());
        return "redirect:/products";
    }


    @GetMapping("/{id}/delete")
    public String deleteItem(@PathVariable("id") Long id){
        Product productToDelete = null;
        
        try {
           productToDelete =  products.stream().filter(x -> (long) id == x.getId() ).findFirst().get();
        } catch (Exception e) {
            // TODO: handle exception
            return "redirect:/products";
        }
        if (productToDelete == null) return "redirect:/products";
        products.remove(productToDelete);
        return "redirect:/products";
    

    }

}