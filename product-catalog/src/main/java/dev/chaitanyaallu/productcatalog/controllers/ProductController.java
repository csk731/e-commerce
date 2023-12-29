package dev.chaitanyaallu.productcatalog.controllers;

import dev.chaitanyaallu.productcatalog.dtos.ExceptionDto;
import dev.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import dev.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import dev.chaitanyaallu.productcatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        List<GenericProductDto> productDtos=productService.getAllProducts();
        if (productDtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productDtos);
        }
        return ResponseEntity.status(HttpStatus.OK).body(productDtos);
    }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") String id) throws NotFoundException{
        GenericProductDto genericProductDto=productService.getProductById(id);
        return genericProductDto;
    }
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),HttpStatus.NOT_FOUND);
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
    }
    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") String id){
        //ResponseEntity<GenericProductDto> responseEntity=new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
        //return responseEntity.getBody();
        return productService.deleteProductById(id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") String id,@RequestBody GenericProductDto product){
        return productService.updateProductById(id, product);
    }
}
