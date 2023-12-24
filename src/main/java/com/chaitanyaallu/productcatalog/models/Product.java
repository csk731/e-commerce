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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    //Mapping table <-> Join table
    @JoinTable(name = "product_orders", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "product_price")
    private Price price;
}
