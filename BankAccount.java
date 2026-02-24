/*=====================================================================
Name: Damien Harmon
Date: February 24, 2026
Assignment: Week 4 PA - Account Balance Calculations
Description: SDC230 Object-Oriented Programming using Java
======================================================================*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("DAMHAR2570 - Week 4 PA Account Balance Calculations.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        double balance = 0.0;
        boolean validStart = false;

        // --- Get starting balance ---
        while (!validStart) {
            try {
                System.out.print("Enter starting balance: ");
                balance = scanner.nextDouble();
                validStart = true;
            } catch (InputMismatchException e) {
                System.out.println("\n--- SYSTEM EXCEPTION CAUGHT ---");
                System.out.println("Exception: " + e);
                System.out.println("ERROR: Please enter a valid numeric value.\n");
                scanner.nextLine(); // clear invalid input
            }
        }

        System.out.println("\nEnter credits (positive) or debits (negative). Enter 0 to quit.\n");

        boolean running = true;

        while (running) {
            try {
                System.out.print("Enter credit/debit: ");
                double amount = scanner.nextDouble();

                if (amount == 0) {
                    running = false;
                    continue;
                }

                // Debit check
                if (amount < 0 && (balance + amount) < 0) {
                    throw new NumberOutOfRangeException(
                        "Debit denied: This transaction would cause the account balance to go negative."
                    );
                }

                // Valid transaction
                balance += amount;
                System.out.printf("Updated balance: %.2f%n%n", balance);

            } catch (InputMismatchException e) {
                System.out.println("\n--- SYSTEM EXCEPTION CAUGHT ---");
                System.out.println("Exception: " + e);
                System.out.println("ERROR: Please enter a valid numeric value.\n");
                scanner.nextLine(); // clear invalid input

            } catch (NumberOutOfRangeException e) {
                System.out.println("\n--- USER EXCEPTION CAUGHT ---");
                System.out.println("Exception: " + e.getMessage());
                System.out.println("ERROR: Transaction rejected.\n");
            }
        }

        System.out.println("\nFinal balance: " + balance);
        System.out.println("Program complete.");
        scanner.close();
    }
}
