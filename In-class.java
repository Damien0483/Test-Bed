/**
 * Damien Harmon
 * February 2026
 * SDC230 Week 3 In‑Class Assignment: Parallel Arrays
 * This class calculates total sales using parallel arrays, input validation,
 * and a loop that continues until the user enters -999.
 */

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("Damien Harmon - Week 3 In-Class Assignment: Parallel Arrays");

        Scanner scanner = new Scanner(System.in);

        // ---------------------------------------------------------
        // PARALLEL ARRAYS (index 0 is a dummy placeholder)
        // ---------------------------------------------------------
        String[] productNames = {
            "DUMMY",
            "Wireless Mouse",
            "USB-C Cable",
            "Laptop Stand",
            "LED Desk Lamp",
            "Web Camera"
        };

        double[] productPrices = {
            0.00,
            2.98,
            4.50,
            9.98,
            4.49,
            6.87
        };

        // ---------------------------------------------------------
        // ACCUMULATORS
        // ---------------------------------------------------------
        double totalSales = 0.0;
        int transactionCount = 0;

        // ---------------------------------------------------------
        // MAIN LOOP
        // ---------------------------------------------------------
        while (true) {

            System.out.print("\nEnter product number (1–5) or -999 to stop: ");
            int productNumber = scanner.nextInt();

            // Sentinel check
            if (productNumber == -999) {
                break;
            }

            // Validate product number
            while (productNumber < 1 || productNumber > 5) {
                System.out.println("ERROR: Invalid product number. Please enter 1–5 or -999 to quit.");
                System.out.print("Enter product number: ");
                productNumber = scanner.nextInt();

                if (productNumber == -999) {
                    break;
                }
            }

            if (productNumber == -999) {
                break;
            }

            // ---------------------------------------------------------
            // QUANTITY VALIDATION LOOP
            // ---------------------------------------------------------
            System.out.print("Enter quantity sold (must be > 0): ");
            int quantity = scanner.nextInt();

            while (quantity <= 0) {
                System.out.println("ERROR: Quantity must be greater than 0.");
                System.out.print("Enter quantity sold: ");
                quantity = scanner.nextInt();
            }

            // ---------------------------------------------------------
            // DRY CALCULATION (only appears once)
            // ---------------------------------------------------------
            double price = productPrices[productNumber];
            double saleAmount = price * quantity;

            totalSales += saleAmount;
            transactionCount++;

            System.out.printf("Added: %s x %d @ $%.2f each → $%.2f%n",
                    productNames[productNumber], quantity, price, saleAmount);
        }

        // ---------------------------------------------------------
        // FINAL OUTPUT
        // ---------------------------------------------------------
        System.out.println("\n===== SALES SUMMARY =====");

        if (transactionCount == 0) {
            System.out.println("No transactions were recorded.");
        } else {
            double average = totalSales / transactionCount;

            System.out.printf("Total Sales: $%.2f%n", totalSales);
            System.out.printf("Average Sale per Transaction: $%.2f%n", average);
        }

        scanner.close();
    }
}
