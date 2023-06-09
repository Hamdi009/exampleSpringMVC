package com.example.springmvcproject.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import com.example.springmvcproject.models.Product;

@Controller
public class ProductController {

    private static List<Product> products = new ArrayList<>();
    private static Long idCount = 0L;
    static {
        products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9", 500D, 50, "samsung-s9.png"));
        products.add(new Product(++idCount, "NK-5P", "Nokia Plus", 60D, 60, null));
        products.add(new Product(++idCount, "IP-7", "Iphone 7", 550D, 30, "iphone-7.png"));
    }

    //@GetMapping(value = {"/", "/products"})
    @RequestMapping("/")
    public String listOfProducts(Model model){
        model.addAttribute("products", products);       
        return "list";
    }
}