package dev.chaitanyaallu.productcatalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductCatalogApplication implements CommandLineRunner {

//	private final ProductRepository productRepository;
//	private final CategoryService categoryService;
//
//	public ProductCatalogApplication(ProductRepository productRepository, CategoryService categoryService) {
//		this.productRepository = productRepository;
//		this.categoryService = categoryService;
//	}

//	private final ProductRepository productRepository;
//	private final CategoryRepository categoryRepository;
//	private final PriceRepository priceRepository;
//
//	public ProductCatalogApplication(ProductRepository productRepository,
//									 CategoryRepository categoryRepository,
//									 PriceRepository priceRepository) {
//		this.productRepository = productRepository;
//		this.categoryRepository = categoryRepository;
//		this.priceRepository = priceRepository;
//	}

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Category category=new Category();
//		category.setCategoryName("Apple Devices");
//		//Category savedCategory=categoryRepository.save(category);
//
//		Price price=new Price();
//		price.setCurrency("INR");
//		price.setPrice(79999);
//		//Price savedPrice=priceRepository.save(price);
//
//		Product product=new Product();
//		product.setProductName("iPhone 15");
//		product.setDescription("Best in the Industry *");
//		product.setCategory(category);
//		product.setPrice(price);
//		productRepository.save(product);
//
//		productRepository.save(new Product("Pixel 7a", "Decent Android Budget Phone",null, new Category("Pixel Devices", null),null, new Price("USD", 350)));
//		productRepository.save(new Product("iPhone 15 Pro Max", "All Rounder",null, new Category("Apple Devices", null),null, new Price("USD", 1099)));
//		productRepository.save(new Product("Pixel 8 pro", "Best Android FlagShip",null, new Category("Pixel Devices", null),null, new Price("USD", 1050)));
//		productRepository.save(new Product("Pixel 6a", "Best Android Budget Mobile",null, new Category("Pixel Devices", null),null, new Price("USD", 250)));

//		productRepository.countAllByPrice_Currency("INR");
//		productRepository.deleteById(UUID.fromString("08faf502-a0e3-41c9-a170-d0077818ecbc"));

//		Category category1=categoryRepository.findById(UUID.fromString("e9fdadac-b5c7-4767-b048-7e7aadb62dff")).get();
//		System.out.println(category1.getCategoryName());
//		System.out.println("All products in this category ");
//		System.out.println("----------------------------------------");
//		category1.getProducts().forEach(System.out::println);

//		List<Product> products=productRepository.findByProductName("iPhone 15");
//		products.forEach(System.out::println);
//		System.out.println(productRepository.findProductById(UUID.fromString("cd872303-17d0-4965-9d7f-f74d04cf49d1")));


//		Category category1 = categoryRepository.findByIdEquals(UUID.fromString("5a05be0d-207a-42ec-9d01-708cf7c609ca"));
//		System.out.println(category1);

//		List<Product> productList=productRepository.findProductsByCategoryId(UUID.fromString("dd9eb971-aa1e-46b1-8e54-71a9e4817ae6"));
//		List<ProductDto> productDtos=categoryService.getProducts(UUID.fromString("dd9eb971-aa1e-46b1-8e54-71a9e4817ae6"));
//		productDtos.forEach(System.out::println);
	}
}
