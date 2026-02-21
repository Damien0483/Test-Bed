import java.util.Scanner;
import java.util.Stack;

public class Week2Calculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("==============================================");
        System.out.println("      Welcome to Damien’s Week 2 Calculator");
        System.out.println("  Where Math Happens Whether You Like It or Not");
        System.out.println("==============================================");

        while (choice != 6) {

            System.out.println("\nMain Menu:");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Postfix Evaluator");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");

            // Validate menu choice
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number 1–6.");
                scanner.nextLine();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {

                case 1:
                    System.out.println("\n--- Addition ---");
                    double a1 = getNumber(scanner, "Enter the first number: ");
                    double b1 = getNumber(scanner, "Enter the second number: ");
                    System.out.println("Result: " + (a1 + b1));
                    break;

                case 2:
                    System.out.println("\n--- Subtraction ---");
                    double a2 = getNumber(scanner, "Enter the first number: ");
                    double b2 = getNumber(scanner, "Enter the second number: ");
                    System.out.println("Result: " + (a2 - b2));
                    break;

                case 3:
                    System.out.println("\n--- Multiplication ---");
                    double a3 = getNumber(scanner, "Enter the first number: ");
                    double b3 = getNumber(scanner, "Enter the second number: ");
                    System.out.println("Result: " + (a3 * b3));
                    break;

                case 4:
                    System.out.println("\n--- Division ---");
                    double a4 = getNumber(scanner, "Enter the first number: ");
                    double b4 = getNumber(scanner, "Enter the second number: ");

                    if (b4 == 0) {
                        System.out.println("Error: Division by zero is not allowed.");
                    } else {
                        System.out.println("Result: " + (a4 / b4));
                    }
                    break;

                case 5:
                    System.out.println("\n--- Postfix Evaluator ---");
                    System.out.print("Enter a postfix expression (with spaces): ");
                    String expression = scanner.nextLine();

                    double result = evaluatePostfix(expression);

                    if (!Double.isNaN(result)) {
                        System.out.println("Postfix Result: " + result);
                    }
                    break;

                case 6:
                    System.out.println("\nExiting program. Stay sharp, math warrior.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1–6.");
            }
        }

        scanner.close();
    }

    // -------------------------
    // Number Input Helper
    // -------------------------
    public static double getNumber(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid number. Try again.");
            scanner.nextLine();
            System.out.print(prompt);
        }
        double num = scanner.nextDouble();
        scanner.nextLine(); // clear newline
        return num;
    }

    // -------------------------
    // Postfix Evaluator
    // -------------------------
    public static double evaluatePostfix(String expression) {

        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {

            if (token.trim().isEmpty()) {
                continue;
            }

            // Try to parse number
            try {
                double num = Double.parseDouble(token);
                stack.push(num);
                continue;
            } catch (NumberFormatException e) {
                // Not a number — must be operator
            }

            // Validate operator
            if (!token.equals("+") && !token.equals("-") &&
                !token.equals("*") && !token.equals("/")) {
                System.out.println("Error: Invalid token → " + token);
                return Double.NaN;
            }

            // Ensure two operands exist
            if (stack.size() < 2) {
                System.out.println("Error: Insufficient operands for operator → " + token);
                return Double.NaN;
            }

            double y = stack.pop();
            double x = stack.pop();
            double result = 0;

            switch (token) {
                case "+":
                    result = x + y;
                    break;

                case "-":
                    result = x - y;
                    break;

                case "*":
                    result = x * y;
                    break;

                case "/":
                    if (y == 0) {
                        System.out.println("Error: Division by zero.");
                        return Double.NaN;
                    }
                    result = x / y;
                    break;
            }

            stack.push(result);
        }

        // Final validation
        if (stack.size() != 1) {
            System.out.println("Error: Malformed expression. Stack leftover: " + stack);
            return Double.NaN;
        }

        return stack.pop();
    }
}
