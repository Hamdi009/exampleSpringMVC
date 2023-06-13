package com.example.springmvcproject.controllers;



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

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.example.springmvcproject.models.Product;
import com.example.springmvcproject.models.ProductForm;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private static List<Product> products = new ArrayList<>();
    private static Long idCount = 0L;
    static {
        products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9", 500D, 50, "samsung-s9.png"));
        products.add(new Product(++idCount, "NK-5P", "Nokia Plus", 60D, 60, null));
        products.add(new Product(++idCount, "IP-7", "Iphone 7", 550D, 30, "iphone-7.png"));
    }

    @GetMapping()
    //@RequestMapping("/")
    public String listOfProducts(Model model){
        model.addAttribute("products", products);       
        return "list";
    }

    @GetMapping("create")
        public String addProduct(Model model){
            model.addAttribute("productForm", new ProductForm());
            return "create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("productForm") @Valid ProductForm productForm, BindingResult bindingResult, @RequestParam("files") MultipartFile[] files){
        if(bindingResult.hasErrors()) return "create";
        // StringBuilder fileName = new StringBuilder();
        // MultipartFile file = files[0];
        // Path filaNameAndPath = Paths.get(upload, null);
        Product product = new Product(++idCount, productForm.getCode(), productForm.getName(), productForm.getPrice(), productForm.getQuantity(), null);
        products.add(product);

        return "redirect:/create";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(){
        return "";
    }

    @GetMapping("/{id}/delete")
    public String deleteItem(@PathVariable("id") Long id){
        Product productToDelete = null;
        for (Product product : products){
            if(product.getId().equals(id)){
                product = productToDelete;
                break;
            }
        }
        if(productToDelete !=null){
            products.remove(productToDelete);
        } 
        return "redirect:/list";
    }

    

}