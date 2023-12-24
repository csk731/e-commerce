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

import java.util.List;

@SpringBootApplication
public class ProductCatalogApplication implements CommandLineRunner {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final PriceRepository priceRepository;

	public ProductCatalogApplication(ProductRepository productRepository,
									 CategoryRepository categoryRepository,
									 PriceRepository priceRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.priceRepository = priceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Category category=new Category();
		category.setCategoryName("Apple Devices");
		//Category savedCategory=categoryRepository.save(category);

		Price price=new Price();
		price.setCurrency("INR");
		price.setPrice(79999);
		//Price savedPrice=priceRepository.save(price);

		Product product=new Product();
		product.setProductName("iPhone 15");
		product.setDescription("Best in the Industry *");
		product.setCategory(category);
		product.setPrice(price);
		productRepository.save(product);

		productRepository.save(new Product("Pixel 7a", "Decent Android Budget Phone",null, new Category("Pixel Devices", null),null, new Price("USD", 350)));
		productRepository.save(new Product("iPhone 15 Pro Max", "All Rounder",null, new Category("Apple Devices", null),null, new Price("USD", 1099)));
		productRepository.save(new Product("Pixel 8 pro", "Best Android FlagShip",null, new Category("Pixel Devices", null),null, new Price("USD", 1050)));
		productRepository.save(new Product("Pixel 6a", "Best Android Budget Mobile",null, new Category("Pixel Devices", null),null, new Price("USD", 250)));

		productRepository.countAllByPrice_Currency("INR");
//		productRepository.deleteById(UUID.fromString("08faf502-a0e3-41c9-a170-d0077818ecbc"));

//		Category category1=categoryRepository.findById(UUID.fromString("e9fdadac-b5c7-4767-b048-7e7aadb62dff")).get();
//		System.out.println(category1.getCategoryName());
//		System.out.println("All products in this category ");
//		System.out.println("----------------------------------------");
//		category1.getProducts().forEach(System.out::println);

		List<Product> products=productRepository.findByProductName("iPhone 15");
		products.forEach(System.out::println);*/
	}
}
