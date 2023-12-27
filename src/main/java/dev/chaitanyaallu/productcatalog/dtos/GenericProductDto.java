package dev.chaitanyaallu.productcatalog.dtos;

import dev.chaitanyaallu.productcatalog.models.Category;
import dev.chaitanyaallu.productcatalog.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDto {
    private String id;
    private String title;
    private double price;
    private Category Category;
    private String description;
    private String image;
}
