import java.util.Scanner;
import java.util.Stack;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("==============================================");
        System.out.println("      Welcome to Damien's Week 3 Calculator");
        System.out.println("  Where Math Happens Whether You Like It or Not");
        System.out.println("==============================================");

        while (choice != 7) {

            System.out.println("\nMain Menu:");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Postfix Evaluator");
            System.out.println("6. Infix Expression Solver");
            System.out.println("7. Quit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number 1-7.");
                scanner.nextLine();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

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

                    try {
                        double result = evaluatePostfix(expression);
                        System.out.println("Postfix Result: " + result);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("\n--- Infix Expression Solver ---");
                    System.out.print("Enter an infix expression (with spaces): ");
                    String infix = scanner.nextLine();

                    try {
                        System.out.println("Step 1: Converting to Postfix...");
                        String postfix = convertToPostfix(infix);
                        System.out.println("Result: " + postfix);

                        System.out.println("Step 2: Evaluating...");
                        double finalAnswer = evaluatePostfix(postfix);
                        System.out.println("Final Answer: " + finalAnswer);

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("\nExiting program. Stay sharp, math warrior.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-7.");
            }
        }

        scanner.close();
    }

    // Number Input Helper
    public static double getNumber(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid number. Try again.");
            scanner.nextLine();
            System.out.print(prompt);
        }
        double num = scanner.nextDouble();
        scanner.nextLine();
        return num;
    }

    // ================================
    // PART 1: INFIX → POSTFIX
    // ================================
    public static String convertToPostfix(String infix) throws IllegalArgumentException {

        if (infix.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be empty.");
        }

        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        String[] tokens = infix.trim().split("\\s+");

        for (String token : tokens) {

            // Number
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                output.append(token).append(" ");
                continue;
            }

            // Single-character operators or parentheses
            if (token.length() == 1) {
                char c = token.charAt(0);

                switch (c) {

                    case '(':
                        stack.push(c);
                        continue;

                    case ')':
                        while (!stack.isEmpty() && stack.peek() != '(') {
                            output.append(stack.pop()).append(" ");
                        }
                        if (stack.isEmpty()) {
                            throw new IllegalArgumentException("Mismatched parentheses detected.");
                        }
                        stack.pop(); // discard '('
                        continue;

                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        while (!stack.isEmpty() &&
                               precedence(stack.peek()) >= precedence(c)) {
                            if (stack.peek() == '(') break;
                            output.append(stack.pop()).append(" ");
                        }
                        stack.push(c);
                        continue;
                }
            }

            throw new IllegalArgumentException("Invalid token: " + token);
        }

        // Cleanup
        while (!stack.isEmpty()) {
            char top = stack.pop();
            if (top == '(') {
                throw new IllegalArgumentException("Mismatched parentheses detected.");
            }
            output.append(top).append(" ");
        }

        return output.toString().trim();
    }

    public static int precedence(char op) {
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return -1;
    }

    // ================================
    // PART 2: POSTFIX EVALUATOR
    // ================================
    public static double evaluatePostfix(String expression) throws ArithmeticException {

        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {

            if (token.trim().isEmpty()) continue;

            try {
                double num = Double.parseDouble(token);
                stack.push(num);
                continue;
            } catch (NumberFormatException e) {
                // Not a number → must be operator
            }

            if (!token.matches("[+\\-*/]")) {
                throw new IllegalArgumentException("Invalid token: " + token);
            }

            if (stack.size() < 2) {
                throw new ArithmeticException("Too many operators or insufficient operands.");
            }

            double y = stack.pop();
            double x = stack.pop();

            switch (token) {
                case "+":
                    stack.push(x + y);
                    break;
                case "-":
                    stack.push(x - y);
                    break;
                case "*":
                    stack.push(x * y);
                    break;
                case "/":
                    if (y == 0) {
                        throw new ArithmeticException("Division by zero is not allowed.");
                    }
                    stack.push(x / y);
                    break;
            }
        }

        if (stack.size() != 1) {
            throw new ArithmeticException("Malformed expression. Stack leftover: " + stack);
        }

        return stack.pop();
    }
}
