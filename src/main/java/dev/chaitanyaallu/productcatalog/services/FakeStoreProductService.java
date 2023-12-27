package dev.chaitanyaallu.productcatalog.services;

import dev.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import dev.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import dev.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore.FakeStoreProductDto;
import dev.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore.FakeStoreProductServiceClient;
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
    public GenericProductDto deleteProductById(String id) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(Long.valueOf(id)));
    }

    @Override
    public GenericProductDto updateProductById(String id, GenericProductDto product) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.updateProductById(Long.valueOf(id), product));
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        return convertToGenericProductDto(fakeStoreProductServiceClient.getProductById(Long.valueOf(id)));
    }
    private GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(String.valueOf(fakeStoreProductDto.getId()));
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        return genericProductDto;
    }
}
