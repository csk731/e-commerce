package dev.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore;

public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String Category;
    private String description;
    private String image;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
