package com.example.springmvcproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springmvcproject.models.Product;

@Controller
public class ProductController {

    private static List<Product> products = new ArrayList<>();
    private static Long idCount = 0L;
    static {
        products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9", 500D, 50, "../../images/samsung-s9.png"));
        products.add(new Product(++idCount, "NK-5P", "Nokia Plus", 60D, 60, null));
        products.add(new Product(++idCount, "IP-7", "Iphone 7", 550D, 30, "../../images/siphone-7.png"));
    }

    @GetMapping(value = {"/", "/products"})
    public String listOfProducts(Model model){
        model.addAttribute("products", products);       
        return "list";
    }
}