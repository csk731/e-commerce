package com.chaitanyaallu.productcatalog.thirdpartyclients.productservices;

import com.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import com.chaitanyaallu.productcatalog.exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyProductServiceClient {
    GenericProductDto getProductById(Long id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(Long id);
    GenericProductDto updateProductById(Long id, GenericProductDto product);
}
