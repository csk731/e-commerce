package com.chaitanyaallu.productcatalog.services;

import com.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import com.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import com.chaitanyaallu.productcatalog.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    GenericProductDto getProductById(UUID id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(UUID id);
    GenericProductDto updateProductById(UUID id, GenericProductDto product);
}
