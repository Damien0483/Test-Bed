/**
 * Name: Your Name
 * Date: 2025-02-10
 * Assignment: SDC330 Performance Assessment - Database
 * Description:
 *     Handles SQLite database connection and CRUD operations for the Addresses table.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDatabase {

    private Connection conn;

    public AddressDatabase(String dbName) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Addresses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                street1 TEXT NOT NULL,
                street2 TEXT,
                city TEXT NOT NULL,
                state TEXT NOT NULL,
                zipcode TEXT NOT NULL
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addAddress(String street1, String street2, String city, String state, String zipcode) {
        String sql = "INSERT INTO Addresses (street1, street2, city, state, zipcode) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, street1);
            pstmt.setString(2, street2);
            pstmt.setString(3, city);
            pstmt.setString(4, state);
            pstmt.setString(5, zipcode);
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Address getAddress(int id) {
        String sql = "SELECT * FROM Addresses WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Address(
                    rs.getInt("id"),
                    rs.getString("street1"),
                    rs.getString("street2"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("zipcode")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Address> getAllAddresses() {
        List<Address> list = new ArrayList<>();
        String sql = "SELECT * FROM Addresses";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Address(
                    rs.getInt("id"),
                    rs.getString("street1"),
                    rs.getString("street2"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("zipcode")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateAddress(int id, String street1, String street2, String city, String state, String zipcode) {
        String sql = """
            UPDATE Addresses
            SET street1 = ?, street2 = ?, city = ?, state = ?, zipcode = ?
            WHERE id = ?
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, street1);
            pstmt.setString(2, street2);
            pstmt.setString(3, city);
            pstmt.setString(4, state);
            pstmt.setString(5, zipcode);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAddress(int id) {
        String sql = "DELETE FROM Addresses WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try { if (conn != null) conn.close(); }
        catch (SQLException e) { e.printStackTrace(); }
    }
}
