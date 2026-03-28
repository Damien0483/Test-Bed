/**
 * Damien Harmon
 * Week 4 In‑Class Assignment
 * SQLiteDatabase class
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {

    private static final String DB_URL = "jdbc:sqlite:products.db";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return conn;
    }
}
