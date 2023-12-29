package dev.chaitanyaallu.productcatalog.services;

import dev.chaitanyaallu.productcatalog.dtos.ProductDto;
import dev.chaitanyaallu.productcatalog.models.Category;
import dev.chaitanyaallu.productcatalog.models.Product;
import dev.chaitanyaallu.productcatalog.repositories.CategoryRepository;
import dev.chaitanyaallu.productcatalog.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@NoArgsConstructor
public class SelfCategoryService implements CategoryService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public SelfCategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    private ProductDto getProductDto(Product product){
        ProductDto productDto=new ProductDto();
        productDto.setProductName(product.getProductName());
        //productDto.setProduct(product);
        return productDto;
    }
    @Override
    public List<ProductDto> getProducts(UUID uuid) {
        List<Product> products=productRepository.findProductsByCategoryId(uuid);
        List<ProductDto> productDtos=new ArrayList<>();
        products.forEach(product -> productDtos.add(getProductDto(product)));
        return productDtos;
    }

    @Override
    public List<String> getProductTitles(List<String> categoryIds) {
        List<Category> categories=new ArrayList<>();
        categoryIds.forEach(id -> categories.add(categoryRepository.getCategoryById(UUID.fromString(id))));
        System.out.println("uuids: "+categories);
        List<Product> products= productRepository.findAllByCategoryIn(categories);
        List<String> titles=new ArrayList<>();
        products.forEach(product -> titles.add(product.getProductName()));
        System.out.println("titles: "+titles);
        return titles;
    }
}