/*========================================================================
Name: Damien Harmon
Date: February 24, 2026
Assignment: Week 4 PA - User Entry of Age
Description: SDC230 Object_Oriented Programming using Java
=========================================================================*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("DAMHAR2570 - Week 4 PA User Entry of Age");

        Scanner scanner = new Scanner(System.in);
        boolean validAgeEntered = false;

        while (!validAgeEntered) {
            try {
                System.out.print("Please enter your age: ");

                int age = scanner.nextInt();  // may trigger InputMismatchException

                // Validate range
                if (age < 1 || age > 100) {
                    throw new NumberOutOfRangeException(
                        "You are definitely born and probably not older than 100 if you are using my program, Try Again!"
                    );
                }

                // If valid, print and exit loop
                System.out.println("Success! The age you entered is: " + age);
                validAgeEntered = true;

            } catch (InputMismatchException e) {
                System.out.println("\n--- SYSTEM EXCEPTION CAUGHT ---");
                System.out.println("Exception: " + e);
                System.out.println("ERROR: Please enter a valid INTEGER value.\n");
                scanner.nextLine(); // clear bad input

            } catch (NumberOutOfRangeException e) {
                System.out.println("\n--- USER EXCEPTION CAUGHT ---");
                System.out.println("Exception: " + e.getMessage());
                System.out.println("ERROR: Age must be between 1 and 100.\n");

            }
        }

        scanner.close();
    }
}
