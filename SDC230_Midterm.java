/**
 * Name: Damien Juaqing Harmon
 * Date: 18 February 2026
 * Assignment: SDC230 Midterm Practical Exam
 * Description:
 *   This program retrieves 10 integers from the user, stores them in a collection,
 *   displays each element with its index, and determines the smallest and largest
 *   values using dedicated methods.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("Damien Harmon - Midterm Practical Exam");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        // --- Get 10 integers from the user ---
        for (int i = 0; i < 10; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            int value = scanner.nextInt();
            numbers.add(value);
        }

        System.out.println();
        System.out.println("Index   Value");
        System.out.println("---------------");

        // --- Display all elements with their index ---
        for (int i = 0; i < numbers.size(); i++) {
            System.out.printf("%-7d %d%n", i, numbers.get(i));
        }

        // --- Determine smallest and largest ---
        int smallest = findSmallest(numbers);
        int largest = findLargest(numbers);

        System.out.println("\nSmallest element: " + smallest);
        System.out.println("Largest element:  " + largest);

        scanner.close();
    }

    // --- Method to find smallest element ---
    public static int findSmallest(ArrayList<Integer> list) {
        int min = list.get(0);
        for (int value : list) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    // --- Method to find largest element ---
    public static int findLargest(ArrayList<Integer> list) {
        int max = list.get(0);
        for (int value : list) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
