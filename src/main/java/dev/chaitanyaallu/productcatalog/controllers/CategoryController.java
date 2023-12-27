package dev.chaitanyaallu.productcatalog.controllers;

import dev.chaitanyaallu.productcatalog.dtos.GetproductTitlesRequestDto;
import dev.chaitanyaallu.productcatalog.dtos.ProductDto;
import dev.chaitanyaallu.productcatalog.models.Product;
import dev.chaitanyaallu.productcatalog.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{uuid}")
    public List<ProductDto> getProducts(@PathVariable("uuid") String uuid){
        return categoryService.getProducts(UUID.fromString(uuid));
    }

    @GetMapping("/titles")
    public List<String> getProductTitles(@RequestBody GetproductTitlesRequestDto request){
        System.out.println(request.getUuids());
        return categoryService.getProductTitles(request.getUuids());
    }
}
