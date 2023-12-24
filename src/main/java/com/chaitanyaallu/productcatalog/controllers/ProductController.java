package com.chaitanyaallu.productcatalog.controllers;

import com.chaitanyaallu.productcatalog.dtos.ExceptionDto;
import com.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import com.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import com.chaitanyaallu.productcatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
    private ProductService productService;
    @Autowired
    // This annotation is optional for constructor injection.
    public ProductController(/*@Value("${productService.type}") @Qualifier("fakeStoreProductService")*/ ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException{
        return productService.getProductById(id);
    }
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),HttpStatus.NOT_FOUND);
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
    }
    @DeleteMapping("/{id}")
    public GenericProductDto deleteproductById(@PathVariable("id") UUID id){
        //ResponseEntity<GenericProductDto> responseEntity=new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
        //return responseEntity.getBody();
        return productService.deleteProductById(id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") UUID id,@RequestBody GenericProductDto product){
        return productService.updateProductById(id, product);
    }
}
