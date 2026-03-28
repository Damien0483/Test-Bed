/**
 * Damien Harmon
 * Week 4 In‑Class Assignment
 * App class
 */

public class App {

    public static void main(String[] args) {

        System.out.println("Damien Harmon, Week 4 In‑Class Assignment – Product Database CRUD\n");

        ProductDb pdb = new ProductDb();
        pdb.createTable();

        // Add products
        pdb.addProduct(new Product("Laptop", "Electronics", 899.99));
        pdb.addProduct(new Product("Desk Chair", "Furniture", 149.50));
        pdb.addProduct(new Product("Water Bottle", "Fitness", 24.99));
        pdb.addProduct(new Product("Notebook", "School Supplies", 3.49));

        // Display all
        System.out.println("All Products in the Database");
        for (Product p : pdb.getAllProducts()) {
            display(p);
        }

        // Invalid ID test
        System.out.println("\nGet a Product Using an Invalid ID");
        int invalidId = -5;
        Product invalid = pdb.getProductById(invalidId);
        if (invalid == null) {
            System.out.println("Product ID " + invalidId + " is an invalid product.");
        }

        // Update product
        Product updated = new Product(2, "Office Chair", "Furniture", 179.99);
        pdb.updateProduct(updated);

        System.out.println("\nUpdated Product");
        display(pdb.getProductById(2));

        // Delete product
        pdb.deleteProduct(2);

        System.out.println("\nAll Products in the Database After Delete");
        for (Product p : pdb.getAllProducts()) {
            display(p);
        }
    }

    private static void display(Product p) {
        System.out.println("Product ID: " + p.getId());
        System.out.println("Name: " + p.getProductName());
        System.out.println("Category: " + p.getCategory());
        System.out.println("Price: $" + p.getPrice() + "\n");
    }
}
