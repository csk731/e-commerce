package com.chaitanyaallu.productcatalog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product extends BaseModel {
    private String productName;
    private String description;
    private String image;
    // Product : Category
    // (L to R) -> 1:1
    // (R to L) -> M:1
    // Ans: Cardinality -> M:1
    @ManyToOne
    private Category category;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Order> orders;
    @OneToOne
    private Price price;
}
