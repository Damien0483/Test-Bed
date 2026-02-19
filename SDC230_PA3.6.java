/**
 * Name: Damien Harmon
 * Date: 18 Feb 2026
 * Assignment: SDC230 Performance Assessment - Calculations & Unique Numbers
 * Description: Demonstrates array storage, unique number extraction using ArrayList,
 *              and overloaded met4hods to calculate totals for collections.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("Damien Harmon - Week 3 PA Calculations & Unique Numbers.");

        Scanner input = new Scanner(System.in);

        int[] numbers = new int[10];
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();

        // Gather 10 integers
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter integer #" + (i + 1) + ": ");
            numbers[i] = input.nextInt();

            if (!uniqueNumbers.contains(numbers[i])) {
                uniqueNumbers.add(numbers[i]);
            }
        }

        // Array calculations
        int arraySum = calculateSum(numbers);
        double arrayAvg = (double) arraySum / numbers.length;

        // ArrayList calculations
        int listSum = calculateSum(uniqueNumbers);
        double listAvg = (double) listSum / uniqueNumbers.size();

        System.out.println("\n--- Array Results ---");
        System.out.println("Count: " + numbers.length);
        System.out.println("Sum: " + arraySum);
        System.out.printf("Average: %.2f%n", arrayAvg);

        System.out.println("\n--- Unique ArrayList Results ---");
        System.out.println("Count: " + uniqueNumbers.size());
        System.out.println("Sum: " + listSum);
        System.out.printf("Average: %.2f%n", listAvg);

        input.close();
    }

    // Sum for array
    public static int calculateSum(int[] arr) {
        int total = 0;
        for (int value : arr) {
            total += value;
        }
        return total;
    }

    // Overloaded sum for ArrayList
    public static int calculateSum(ArrayList<Integer> list) {
        int total = 0;
        for (int value : list) {
            total += value;
        }
        return total;
    }
}
