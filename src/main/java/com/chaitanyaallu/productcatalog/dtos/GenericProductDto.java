package com.chaitanyaallu.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private double price;
    private String Category;
    private String description;
    private String image;
}
