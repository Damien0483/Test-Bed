/**
 * Damien Harmon
 * Week 4 In‑Class Assignment
 * ProductDb class
 */

import java.sql.*;
import java.util.ArrayList;

public class ProductDb {

    private SQLiteDatabase db;

    public ProductDb() {
        db = new SQLiteDatabase();
    }

    // Create table
    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Products (
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                ProductName TEXT NOT NULL,
                Category TEXT NOT NULL,
                Price REAL NOT NULL
            );
        """;

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Create table error: " + e.getMessage());
        }
    }

    // Add product
    public void addProduct(Product p) {
        String sql = "INSERT INTO Products (ProductName, Category, Price) VALUES (?, ?, ?)";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getProductName());
            pstmt.setString(2, p.getCategory());
            pstmt.setDouble(3, p.getPrice());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    // Update product
    public void updateProduct(Product p) {
        String sql = "UPDATE Products SET ProductName = ?, Category = ?, Price = ? WHERE ID = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getProductName());
            pstmt.setString(2, p.getCategory());
            pstmt.setDouble(3, p.getPrice());
            pstmt.setInt(4, p.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    // Delete product
    public void deleteProduct(int id) {
        String sql = "DELETE FROM Products WHERE ID = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }

    // Get all products
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                    rs.getInt("ID"),
                    rs.getString("ProductName"),
                    rs.getString("Category"),
                    rs.getDouble("Price")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Select all error: " + e.getMessage());
        }

        return list;
    }

    // Get product by ID
    public Product getProductById(int id) {
        String sql = "SELECT * FROM Products WHERE ID = ?";
        Product p = null;

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                p = new Product(
                    rs.getInt("ID"),
                    rs.getString("ProductName"),
                    rs.getString("Category"),
                    rs.getDouble("Price")
                );
            }

        } catch (SQLException e) {
            System.out.println("Select by ID error: " + e.getMessage());
        }

        return p;
    }
}
