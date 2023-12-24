package com.chaitanyaallu.productcatalog.services;

import com.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import com.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import com.chaitanyaallu.productcatalog.models.Product;
import com.chaitanyaallu.productcatalog.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService{

    private final ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private GenericProductDto getGenericProductDtoFrom(Product product){
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setTitle(product.getProductName());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setCategory(product.getCategory());
        return genericProductDto;
    }

    private Product getProductFrom(GenericProductDto genericProductDto){
        Product product=new Product();
        product.setId(genericProductDto.getId());
        product.setProductName(genericProductDto.getTitle());
        product.setPrice(genericProductDto.getPrice());
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());
        product.setCategory(genericProductDto.getCategory());
        return product;
    }

    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
        Product product=productRepository.findProductById(id);
        return getGenericProductDtoFrom(product);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product savedProduct=productRepository.save(getProductFrom(genericProductDto));
        return getGenericProductDtoFrom(savedProduct);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products=productRepository.findAll();
        List<GenericProductDto> genericProductDtos=new ArrayList<>();
        for (Product product:products){
            genericProductDtos.add(getGenericProductDtoFrom(product));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(UUID id) {
        Product deletedProduct=productRepository.deleteProductById(id);
        return getGenericProductDtoFrom(deletedProduct);
    }

    @Override
    public GenericProductDto updateProductById(UUID id, GenericProductDto genericProductDto) {
        Product updatedProduct=productRepository.save(getProductFrom(genericProductDto));
        return getGenericProductDtoFrom(updatedProduct);
    }
}
