import java.util.Scanner;

public class TriangleTest {

    public static void main(String[] args) {

        System.out.println("Your Name - Triangle Printing Assignment\n");

        Scanner input = new Scanner(System.in);

        int userSize;

        System.out.print("Enter initial triangle size (-9999 to quit): ");
        userSize = input.nextInt();

        // Sentinel check before creating object
        if (userSize == -9999) {
            System.out.println("Program terminated.");
            return;
        }

        DrawTriangle triangle = new DrawTriangle(userSize);

        int choice = 0;

        while (choice != -9999) {

            System.out.println("\nCurrent Triangle Size: " + triangle.getSize());
            System.out.println("Menu Options:");
            System.out.println("1 - Draw Pattern A");
            System.out.println("2 - Draw Pattern B");
            System.out.println("3 - Draw Pattern C");
            System.out.println("4 - Draw Pattern D");
            System.out.println("5 - Change Triangle Size");
            System.out.println("-9999 - Quit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nPattern A:");
                    triangle.drawPatternA();
                    break;

                case 2:
                    System.out.println("\nPattern B:");
                    triangle.drawPatternB();
                    break;

                case 3:
                    System.out.println("\nPattern C:");
                    triangle.drawPatternC();
                    break;

                case 4:
                    System.out.println("\nPattern D:");
                    triangle.drawPatternD();
                    break;

                case 5:
                    System.out.print("Enter new triangle size: ");
                    int newSize = input.nextInt();
                    triangle.setSize(newSize);
                    System.out.println("Triangle size updated to: " + triangle.getSize());
                    break;

                case -9999:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        input.close();
    }
}
