package dev.chaitanyaallu.productcatalog.dtos;

import dev.chaitanyaallu.productcatalog.models.Product;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto{
    private String productName;
    //private Product product;
}
