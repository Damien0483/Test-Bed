/**
 * Name: Your Name
 * Date: 2025-02-10
 * Assignment: SDC330 Performance Assessment - Database
 * Description:
 *     Demonstrates creating a database, inserting, reading, updating,
 *     and deleting address records.
 */

public class App {

    public static void main(String[] args) {

        System.out.println("Your Name - Week 4 Database PA");

        AddressDatabase db = new AddressDatabase("yourname.db");
        db.createTable();

        // Add 4 records
        db.addAddress("123 Main St", "", "Norfolk", "VA", "23502");
        db.addAddress("456 Oak Ave", "Apt 12", "Chesapeake", "VA", "23320");
        db.addAddress("789 Pine Rd", "", "Virginia Beach", "VA", "23452");
        db.addAddress("101 Maple Dr", "Suite 200", "Portsmouth", "VA", "23704");

        // Print all addresses
        System.out.println("\n--- Printing All Addresses ---");
        for (Address a : db.getAllAddresses()) {
            System.out.println(a + "\n");
        }

        // Invalid ID test
        System.out.println("--- Attempting to Retrieve Invalid ID (999) ---");
        Address invalid = db.getAddress(999);
        System.out.println(invalid != null ? invalid : "No address found with ID 999");

        // Update a record
        System.out.println("\n--- Updating Address with ID 2 ---");
        db.updateAddress(2, "456 Oak Ave", "Unit 99", "Chesapeake", "VA", "23320");
        System.out.println(db.getAddress(2));

        // Delete a record
        System.out.println("\n--- Deleting Address with ID 3 ---");
        db.deleteAddress(3);

        // Print all remaining
        System.out.println("\n--- Printing All Addresses After Deletion ---");
        for (Address a : db.getAllAddresses()) {
            System.out.println(a + "\n");
        }

        db.close();
    }
}
