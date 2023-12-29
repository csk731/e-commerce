package dev.chaitanyaallu.productcatalog.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MyOrder")
public class Order extends BaseModel{
    @ManyToMany(mappedBy = "orders")
    private List<Product> products;
}
