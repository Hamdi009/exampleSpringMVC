package com.example.springmvcproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springmvcproject.models.Product;
import com.example.springmvcproject.repositories.ProductRepository;

public class productServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product addProduct(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(long id, Product p) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product exisitingProduct = optionalProduct.get();
            exisitingProduct.setName(p.getName());
            exisitingProduct.setCode(p.getCode());
            exisitingProduct.setPrice(p.getPrice());
            exisitingProduct.setQuantity(p.getQuantity());
            exisitingProduct.setCategory(p.getCategory());

            return productRepository.save(exisitingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> gettAllProduct() {
       return productRepository.findAll();
    }
    
}