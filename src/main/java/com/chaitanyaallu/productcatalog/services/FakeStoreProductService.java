package com.chaitanyaallu.productcatalog.services;

import com.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import com.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import com.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore.FakeStoreProductServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakeStoreProductService")
@AllArgsConstructor
public class FakeStoreProductService implements ProductService {

    //private ThirdPartyProductServiceClient thirdPartyProductServiceClient;
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return fakeStoreProductServiceClient.createProduct(product);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return fakeStoreProductServiceClient.getAllProducts();
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return fakeStoreProductServiceClient.deleteProductById(id);
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        return fakeStoreProductServiceClient.updateProductById(id, product);
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException{
        return fakeStoreProductServiceClient.getProductById(id);
    }
}
