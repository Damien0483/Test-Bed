import java.util.Scanner;

public class App{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Project header
        System.out.println("=== Damien Harmon - Project Week 1: Basic Calculator ===\n");

        // Welcome message
        System.out.println("Welcome! This calculator will let you:");
        System.out.println("- Add two integers");
        System.out.println("- Subtract two floating‑point numbers\n");

        // Integer operations
        System.out.println("INTEGER ADDITION");
        System.out.print("Enter the first integer: ");
        int int1 = scanner.nextInt();

        System.out.print("Enter the second integer: ");
        int int2 = scanner.nextInt();

        int intResult = int1 + int2;
        System.out.println("Result: " + int1 + " + " + int2 + " = " + intResult + "\n");

        // Floating‑point operations
        System.out.println("FLOATING‑POINT SUBTRACTION");
        System.out.print("Enter the first floating‑point value: ");
        double f1 = scanner.nextDouble();

        System.out.print("Enter the second floating‑point value: ");
        double f2 = scanner.nextDouble();

        double floatResult = f2 - f1;
        System.out.printf("Result: %.4f - %.4f = %.4f%n%n", f2, f1, floatResult);

        // Closing message
        System.out.println("Thank you for using Damien’s Calculator!");
        scanner.close();
    }
}
