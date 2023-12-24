package com.chaitanyaallu.productcatalog.dtos;

import com.chaitanyaallu.productcatalog.models.Category;
import com.chaitanyaallu.productcatalog.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDto {
    private UUID id;
    private String title;
    private Price price;
    private com.chaitanyaallu.productcatalog.models.Category Category;
    private String description;
    private String image;
}
