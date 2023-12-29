package dev.chaitanyaallu.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class GenericProductDto {
    private String id;
    private String title;
    private double price;
    private String Category;
    private String description;
    private String image;
}
