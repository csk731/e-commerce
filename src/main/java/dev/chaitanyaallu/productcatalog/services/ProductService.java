package dev.chaitanyaallu.productcatalog.services;

import dev.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import dev.chaitanyaallu.productcatalog.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    GenericProductDto getProductById(String id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(String id);
    GenericProductDto updateProductById(String id, GenericProductDto product);
}
