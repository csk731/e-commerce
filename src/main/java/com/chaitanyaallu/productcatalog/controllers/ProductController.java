package com.chaitanyaallu.productcatalog.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public void getAllProducts(){

    }
    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id){
        return "This is a product with id "+id;
    }
    @DeleteMapping("/{id}")
    public void deleteproductById(@PathVariable("id") Long id){

    }
    @PostMapping
    public void createProduct(){

    }
    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }
}
