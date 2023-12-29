package dev.chaitanyaallu.productcatalog.repositories;

import dev.chaitanyaallu.productcatalog.models.Category;
import dev.chaitanyaallu.productcatalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findProductById(UUID id);
    Product save(Product product);
    List<Product> findAll();
    Product deleteProductById(UUID id);
    List<Product> findByProductName(String productName);

    void countAllByPrice_Currency(String inr);
    List<Product> findProductsByCategoryId(UUID uuid);

    List<Product> findAllByCategoryIn(List<Category> categories);
    /*Product findProductByProductNameEquals(String productName);
    Product findByProductNameAndPrice_Price(String productName, double price);
    List<Product> findProductsByPrice_Currency(String currency);
    Long countAllByPrice_Currency(String currency);
    List<Product> findAllByProductNameLike(String productNameRegex);
    List<Product> readAllByProductNameLike(String productNameRegex);
    boolean existsByProductNameStartingWith(String productNamePrefix);
    @Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Product> findByProductName(String productName);
    @Query(CustomQueries.READ_ALL_BY_TITLE)
    List<Product> readByProductName(String productName);*/

}
