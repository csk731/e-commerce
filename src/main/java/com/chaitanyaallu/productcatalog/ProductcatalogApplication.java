package com.chaitanyaallu.productcatalog;

import com.chaitanyaallu.productcatalog.models.Category;
import com.chaitanyaallu.productcatalog.models.Price;
import com.chaitanyaallu.productcatalog.models.Product;
import com.chaitanyaallu.productcatalog.repositories.CategoryRepository;
import com.chaitanyaallu.productcatalog.repositories.PriceRepository;
import com.chaitanyaallu.productcatalog.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ProductcatalogApplication implements CommandLineRunner {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final PriceRepository priceRepository;

	public ProductcatalogApplication(ProductRepository productRepository,
									 CategoryRepository categoryRepository,
									 PriceRepository priceRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.priceRepository = priceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductcatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category=new Category();
		category.setCategoryName("Apple Devices");
		Category savedCategory=categoryRepository.save(category);

		Price price=new Price();
		price.setCurrency("INR");
		price.setPrice(100.50);
		Price savedPrice=priceRepository.save(price);

		Product product=new Product();
		product.setProductName("iPhone");
		product.setDescription("Best in the Industry");
		product.setCategory(savedCategory);
		product.setPrice(savedPrice);
		productRepository.save(product);

		Category category1=categoryRepository.findById(UUID.fromString("41bc382e-e3cf-4332-9e95-8e363262e849")).get();
		System.out.println(category1.getCategoryName());
		System.out.println("All products in this category ");
		System.out.println("----------------------------------------");
		category1.getProducts().forEach(System.out::println);
	}
}
