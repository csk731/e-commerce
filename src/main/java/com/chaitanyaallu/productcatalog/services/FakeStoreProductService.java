package com.chaitanyaallu.productcatalog.services;

import com.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore.FakeStoreProductDto;
import com.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import com.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import com.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore.FakeStoreProductServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
@Primary
@AllArgsConstructor
public class FakeStoreProductService implements ProductService {

    //private ThirdPartyProductServiceClient thirdPartyProductServiceClient;
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return convertToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos=new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductServiceClient.getAllProducts()){
            genericProductDtos.add(convertToGenericProductDto(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.updateProductById(id, product));
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException{
        return convertToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }
    private GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        return genericProductDto;
    }
}
