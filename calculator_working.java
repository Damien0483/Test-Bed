import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FloorCalculator {

    // -----------------------------
    // Data Models
    // -----------------------------
    static class RoomSection {
        double lengthInches;
        double widthInches;

        RoomSection(double lengthInches, double widthInches) {
            this.lengthInches = lengthInches;
            this.widthInches = widthInches;
        }

        double getAreaSqFt() {
            double lengthFt = lengthInches / 12.0;
            double widthFt = widthInches / 12.0;
            return lengthFt * widthFt;
        }
    }

    // -----------------------------
    // Utility Methods
    // -----------------------------
    public static double roundUp(double value) {
        return Math.ceil(value);
    }

    public static boolean isExit(String s) {
        return s.equalsIgnoreCase("exit") || s.equalsIgnoreCase("quit");
    }

    public static boolean isClear(String s) {
        return s.equalsIgnoreCase("clear");
    }

    public static double getNumericInput(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (isExit(input)) {
                System.out.println("Goodbye! Thanks for using Damien’s Flooring Calculator.");
                System.exit(0);
            }

            if (isClear(input)) {
                return Double.NEGATIVE_INFINITY; // signal to caller
            }

            try {
                double value = Double.parseDouble(input);
                return value;
            } catch (Exception e) {
                System.out.println("Invalid entry! Please enter only numbers in inches to two decimal places.");
            }
        }
    }

    // -----------------------------
    // Main Program
    // -----------------------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // -----------------------------------------
        // Startup Banner
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

        while (true) {

            List<RoomSection> sections = new ArrayList<>();
            double wastePercent = 0;
            double costPerUnit = 0;
            String material = "";
            double trimFeet = 0;

            System.out.println("\n--- New Room Calculation ---");
            System.out.println("Type Clear at any time to reset the room.");
            System.out.println("Type Exit or Quit to close the program.\n");

            // -----------------------------------------
            // Number of sections
            // -----------------------------------------
            int numSections = 0;
            while (numSections <= 0) {
                double val = getNumericInput(sc, "Enter number of rectangular sections: ");
                if (val == Double.NEGATIVE_INFINITY) {
                    System.out.println("Room cleared.");
                    continue;
                }
                if (val <= 0) {
                    System.out.println("Value must be greater than zero.");
                } else {
                    numSections = (int) val;
                }
            }

            // -----------------------------------------
            // Section dimensions
            // -----------------------------------------
            for (int i = 1; i <= numSections; i++) {
                System.out.println("\n--- Section " + i + " ---");

                double length = -1;
                while (length <= 0) {
                    double val = getNumericInput(sc, "Enter length in inches: ");
                    if (val == Double.NEGATIVE_INFINITY) {
                        System.out.println("Room cleared.");
                        i = 0;
                        sections.clear();
                        break;
                    }
                    if (val <= 0) {
                        System.out.println("Length must be greater than zero.");
                    } else {
                        length = val;
                    }
                }
                if (i == 0) continue;

                double width = -1;
                while (width <= 0) {
                    double val = getNumericInput(sc, "Enter width in inches: ");
                    if (val == Double.NEGATIVE_INFINITY) {
                        System.out.println("Room cleared.");
                        i = 0;
                        sections.clear();
                        break;
                    }
                    if (val <= 0) {
                        System.out.println("Width must be greater than zero.");
                    } else {
                        width = val;
                    }
                }
                if (i == 0) continue;

                sections.add(new RoomSection(length, width));
            }

            if (sections.isEmpty()) continue;

            // -----------------------------------------
            // Material selection
            // -----------------------------------------
            while (true) {
                System.out.print("\nEnter material (Laminate, Hardwood, Tile, Carpet): ");
                material = sc.nextLine().trim();

                if (isExit(material)) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                if (isClear(material)) {
                    System.out.println("Room cleared.");
                    continue;
                }

                if (material.equalsIgnoreCase("Laminate") ||
                    material.equalsIgnoreCase("Hardwood") ||
                    material.equalsIgnoreCase("Tile") ||
                    material.equalsIgnoreCase("Carpet")) {
                    break;
                }

                System.out.println("Invalid material. Try again.");
            }

            // -----------------------------------------
            // Waste percentage
            // -----------------------------------------
            while (true) {
                double val = getNumericInput(sc, "Enter waste percentage (0–30): ");
                if (val == Double.NEGATIVE_INFINITY) {
                    System.out.println("Room cleared.");
                    continue;
                }
                if (val < 0 || val > 30) {
                    System.out.println("Waste must be between 0 and 30.");
                } else {
                    wastePercent = val;
                    break;
                }
            }

            // -----------------------------------------
            // Cost per unit
            // -----------------------------------------
            while (true) {
                double val = getNumericInput(sc, "Enter cost per unit (sq ft or sq yd): ");
                if (val == Double.NEGATIVE_INFINITY) {
                    System.out.println("Room cleared.");
                    continue;
                }
                if (val < 0) {
                    System.out.println("Cost cannot be negative.");
                } else {
                    costPerUnit = val;
                    break;
                }
            }

            // -----------------------------------------
            // Optional trim
            // -----------------------------------------
            while (true) {
                System.out.print("Enter trim footage (or press Enter to skip): ");
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    trimFeet = 0;
                    break;
                }

                if (isExit(input)) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                if (isClear(input)) {
                    System.out.println("Room cleared.");
                    continue;
                }

                try {
                    double val = Double.parseDouble(input);
                    if (val < 0) {
                        System.out.println("Trim cannot be negative.");
                    } else {
                        trimFeet = val;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid entry! Please enter only numbers.");
                }
            }

            // -----------------------------------------
            // Calculations
            // -----------------------------------------
            double totalSqFt = 0;
            System.out.println("\n--- Calculations ---");

            for (int i = 0; i < sections.size(); i++) {
                RoomSection s = sections.get(i);
                double area = s.getAreaSqFt();

                System.out.printf("Section %d: (%.2f in ÷ 12) × (%.2f in ÷ 12) = %.2f sq ft%n",
                        i + 1, s.lengthInches, s.widthInches, area);

                totalSqFt += area;
            }

            double roundedSqFt = roundUp(totalSqFt);
            System.out.println("Total Area (rounded up): " + roundedSqFt + " sq ft");

            double wasteAdjusted = roundUp(roundedSqFt * (1 + wastePercent / 100.0));
            System.out.println("Waste‑Adjusted Area: " + wasteAdjusted + " sq ft");

            double materialUnits = wasteAdjusted;

            if (material.equalsIgnoreCase("Carpet")) {
                materialUnits = roundUp(wasteAdjusted / 9.0);
                System.out.println("Converted to Square Yards: " + materialUnits + " sq yd");
            }

            double totalCost = roundUp(materialUnits * costPerUnit);

            // -----------------------------------------
            // Summary
            // -----------------------------------------
            System.out.println("\n--- Summary ---");
            System.out.println("Material: " + material);
            System.out.println("Total Square Footage: " + roundedSqFt);
            System.out.println("Waste‑Adjusted Square Footage: " + wasteAdjusted);
            System.out.println("Material Required: " + materialUnits + (material.equalsIgnoreCase("Carpet") ? " sq yd" : " sq ft"));
            System.out.println("Cost: $" + totalCost);
            if (trimFeet > 0) {
                System.out.println("Trim Footage: " + trimFeet + " ft");
            }

            System.out.println("\nIf zombies interrupt your flooring project, please evacuate the building before continuing calculations.");
            System.out.println("\n--- End of Room ---\n");
        }
    }
}
