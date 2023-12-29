package dev.chaitanyaallu.productcatalog.services;

import dev.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import dev.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import dev.chaitanyaallu.productcatalog.models.Category;
import dev.chaitanyaallu.productcatalog.models.Price;
import dev.chaitanyaallu.productcatalog.models.Product;
import dev.chaitanyaallu.productcatalog.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService{

    private final ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private GenericProductDto getGenericProductDtoFrom(Product product){
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(product.getId().toString());
        genericProductDto.setTitle(product.getProductName());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setCategory(product.getCategory().getCategoryName());
        return genericProductDto;
    }

    private Product getProductFrom(GenericProductDto genericProductDto){
        Product product=new Product();
        product.setId(UUID.fromString(genericProductDto.getId()));
        product.setProductName(genericProductDto.getTitle());
        product.setPrice(new Price("",genericProductDto.getPrice()));
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());
        product.setCategory(new Category(genericProductDto.getCategory(),null));
        return product;
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        Product product=productRepository.findProductById(UUID.fromString(id));
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
    public GenericProductDto deleteProductById(String id) {
        Product deletedProduct=productRepository.deleteProductById(UUID.fromString(id));
        return getGenericProductDtoFrom(deletedProduct);
    }

    @Override
    public GenericProductDto updateProductById(String id, GenericProductDto genericProductDto) {
        Product updatedProduct=productRepository.save(getProductFrom(genericProductDto));
        return getGenericProductDtoFrom(updatedProduct);
    }
}
