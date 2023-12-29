package dev.chaitanyaallu.productcatalog.services;

import dev.chaitanyaallu.productcatalog.dtos.ProductDto;
import dev.chaitanyaallu.productcatalog.models.Category;
import dev.chaitanyaallu.productcatalog.models.Product;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    public List<ProductDto> getProducts(UUID uuid);

    List<String> getProductTitles(List<String> categoryIds);
}
