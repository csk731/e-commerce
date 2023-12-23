package com.chaitanyaallu.productcatalog.services;

import com.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import com.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        return null;
    }
}
