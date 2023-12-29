package dev.chaitanyaallu.productcatalog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModel {
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
