import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        // Informative header
        System.out.println("=== Project Week 2: Interactive Calculator – Created by [Your Name] ===\n");

        // Welcome message
        System.out.println("Welcome to the Project Week 2 Calculator!");
        System.out.println("You can add, subtract, multiply, or divide two numbers.");
        System.out.println("Rules:");
        System.out.println(" - No division by zero");
        System.out.println(" - Subtraction cannot result in a negative number");
        System.out.println(" - Division results must be at least 1");
        System.out.println(" - The calculator will continue until you choose to quit\n");

        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Menu
            System.out.println("\nPlease choose an operation:");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Quit");
            System.out.print("Enter your choice (1–5): ");

            String choice = input.nextLine();

            if (choice.equals("5")) {
                running = false;
                break;
            }

            // Validate menu choice
            if (!choice.matches("[1-4]")) {
                System.out.println("Invalid selection. Please choose a valid option.");
                continue;
            }

            double num1, num2;

            try {
                System.out.print("Enter the first number: ");
                num1 = Double.parseDouble(input.nextLine());

                System.out.print("Enter the second number: ");
                num2 = Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values.");
                continue;
            }

            switch (choice) {
                case "1": // Addition
                    double add = num1 + num2;
                    System.out.println("Result: " + num1 + " + " + num2 + " = " + add);
                    break;

                case "2": // Subtraction
                    if (num1 - num2 < 0) {
                        System.out.println("Invalid operation: subtraction result cannot be negative.");
                    } else {
                        double subtract = num1 - num2;
                        System.out.println("Result: " + num1 + " - " + num2 + " = " + subtract);
                    }
                    break;

                case "3": // Multiplication
                    double multiply = num1 * num2;
                    System.out.println("Result: " + num1 + " * " + num2 + " = " + multiply);
                    break;

                case "4": // Division
                    if (num2 == 0) {
                        System.out.println("Invalid operation: cannot divide by zero.");
                    } else {
                        double divide = num1 / num2;
                        if (divide < 1) {
                            System.out.println("Invalid operation: division result must be at least 1.");
                        } else {
                            System.out.println("Result: " + num1 + " / " + num2 + " = " + divide);
                        }
                    }
                    break;
            }
        }

        // Closing message
        System.out.println("\nThank you for using the Project Week 2 Calculator!");
        input.close();
    }
}
