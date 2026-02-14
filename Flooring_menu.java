import java.util.Scanner;

public class FloorCalculator {

    public static boolean isExit(String s) {
        return s.equalsIgnoreCase("exit") || s.equalsIgnoreCase("quit");
    }

    public static boolean isClear(String s) {
        return s.equalsIgnoreCase("clear");
    }

    public static double getNumber(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (isExit(input)) {
                System.out.println("Goodbye! Thanks for using Damien’s Flooring Calculator.");
                System.exit(0);
            }

            if (isClear(input)) {
                return Double.NEGATIVE_INFINITY;
            }

            try {
                return Double.parseDouble(input);
            } catch (Exception e) {
                System.out.println("Invalid entry! Please enter only numbers.");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // -----------------------------------------
        // YOUR ORIGINAL WELCOME MESSAGE
        // -----------------------------------------
        System.out.println("===============================================");
        System.out.println("      Welcome to Damien’s Flooring Calculator!!!");
        System.out.println("===============================================");
        System.out.println();
        System.out.println("Measuring a room always feels a bit like preparing for a home‑improvement safari.");
        System.out.println("You stride in with your tape measure like an explorer entering uncharted territory,");
        System.out.println("hoping the floor won’t reveal any surprise “bonus” corners the architect dreamed up");
        System.out.println("on a Friday afternoon.");
        System.out.println();
        System.out.println("You’ll want the usual gear:");
        System.out.println("- A tape measure long enough to reach the far wall without snapping back");
        System.out.println("- A pencil you will absolutely lose within five minutes");
        System.out.println("- Paper for sketching the room, even though it will end up looking like a treasure map");
        System.out.println("- A calculator (or this program) to make sense of the numbers");
        System.out.println("- And a healthy sense of optimism, because every room is 10% bigger once you start paying for flooring");
        System.out.println();
        System.out.println("Armed with those tools, you're ready to measure like a pro—or at least look convincingly busy while doing it.");
        System.out.println();

        // -----------------------------------------
        // MAIN LOOP — YOUR STRUCTURE
        // -----------------------------------------
        while (true) {

            System.out.println("\n--- New Room Calculation ---");
            System.out.println("Type Clear at any time to reset the room.");
            System.out.println("Type Exit or Quit to close the program.\n");

            // -----------------------------------------
            // YOUR FLOORING MENU — UNCHANGED
            // -----------------------------------------
            double sections = getNumber(sc, "Enter number of rectangular sections: ");
            if (sections == Double.NEGATIVE_INFINITY) {
                System.out.println("Room cleared.");
                continue;
            }

            double length = getNumber(sc, "Enter length in inches: ");
            if (length == Double.NEGATIVE_INFINITY) {
                System.out.println("Room cleared.");
                continue;
            }

            double width = getNumber(sc, "Enter width in inches: ");
            if (width == Double.NEGATIVE_INFINITY) {
                System.out.println("Room cleared.");
                continue;
            }

            System.out.print("Enter material (Laminate, Hardwood, Tile, Carpet): ");
            String material = sc.nextLine().trim();
            if (isExit(material)) {
                System.out.println("Goodbye!");
                break;
            }
            if (isClear(material)) {
                System.out.println("Room cleared.");
                continue;
            }

            double waste = getNumber(sc, "Enter waste percentage (0–30): ");
            if (waste == Double.NEGATIVE_INFINITY) {
                System.out.println("Room cleared.");
                continue;
            }

            double cost = getNumber(sc, "Enter cost per unit: ");
            if (cost == Double.NEGATIVE_INFINITY) {
                System.out.println("Room cleared.");
                continue;
            }

            System.out.print("Enter trim footage (or press Enter to skip): ");
            String trimInput = sc.nextLine().trim();
            double trim = 0;
            if (isExit(trimInput)) {
                System.out.println("Goodbye!");
                break;
            }
            if (isClear(trimInput)) {
                System.out.println("Room cleared.");
                continue;
            }
            if (!trimInput.isEmpty()) {
                try {
                    trim = Double.parseDouble(trimInput);
                } catch (Exception e) {
                    System.out.println("Invalid trim value. Setting trim to 0.");
                }
            }

            // -----------------------------------------
            // ASSIGNMENT CALCULATIONS (REPLACES FLOORING MATH)
            // -----------------------------------------
            System.out.println("\n--- Calculations ---");

            double result = 0;
            boolean valid = true;

            // For the assignment, we perform ONE operation:
            // length = first number
            // width  = second number

            System.out.println("Choose operation:");
            System.out.println("1. Add length + width");
            System.out.println("2. Subtract length - width");
            System.out.println("3. Multiply length × width");
            System.out.println("4. Divide length ÷ width");

            System.out.print("Enter choice: ");
            String op = sc.nextLine().trim();

            if (isExit(op)) {
                System.out.println("Goodbye!");
                break;
            }

            switch (op) {
                case "1":
                    result = length + width;
                    break;

                case "2":
                    if (length - width < 0) {
                        System.out.println("Invalid: subtraction result cannot be negative.");
                        valid = false;
                    } else {
                        result = length - width;
                    }
                    break;

                case "3":
                    result = length * width;
                    break;

                case "4":
                    if (width == 0) {
                        System.out.println("Invalid: division by zero is not allowed.");
                        valid = false;
                    } else if ((length / width) < 1) {
                        System.out.println("Invalid: division result cannot be less than one.");
                        valid = false;
                    } else {
                        result = length / width;
                    }
                    break;

                default:
                    System.out.println("Invalid operation.");
                    valid = false;
            }

            // -----------------------------------------
            // SUMMARY — YOUR STYLE
            // -----------------------------------------
            System.out.println("\n--- Summary ---");
            System.out.println("Material: " + material);
            System.out.println("Sections: " + sections);
            System.out.println("Length: " + length + " in");
            System.out.println("Width: " + width + " in");
            System.out.println("Waste: " + waste + "%");
            System.out.println("Cost: $" + cost);
            System.out.println("Trim: " + trim + " ft");

            if (valid) {
                System.out.println("Operation Result: " + result);
            }

            System.out.println("\nIf zombies interrupt your flooring project, please evacuate before continuing calculations.");
            System.out.println("\n--- End of Room ---\n");
        }

        sc.close();
    }
}
