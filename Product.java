/**
 * Damien Harmon
 * Week 4 In‑Class Assignment
 * Product class
 */

public class Product {

    private int id;
    private String productName;
    private String category;
    private double price;

    // No‑argument constructor
    public Product() {
        setId(0);
        setProductName("");
        setCategory("");
        setPrice(0.0);
    }

    // Constructor with all fields
    public Product(int id, String productName, String category, double price) {
        setId(id);
        setProductName(productName);
        setCategory(category);
        setPrice(price);
    }

    // Constructor without ID
    public Product(String productName, String category, double price) {
        setProductName(productName);
        setCategory(category);
        setPrice(price);
    }

    // Getters
    public int getId() { return id; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
}
