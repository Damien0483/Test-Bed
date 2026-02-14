import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class RoomSection {
    double length;
    double width;

    RoomSection(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double getArea() {
        return length * width;
    }
}

class Material {
    String type;
    double costPerUnit;
    boolean usesSquareYards;

    Material(String type, double costPerUnit, boolean usesSquareYards) {
        this.type = type;
        this.costPerUnit = costPerUnit;
        this.usesSquareYards = usesSquareYards;
    }
}

class FlooringCalculator {

    double calculateTotalArea(List<RoomSection> sections) {
        double total = 0;
        for (RoomSection s : sections) {
            total += s.getArea();
        }
        return total;
    }

    double applyWaste(double area, double wastePercent) {
        return area * (1 + (wastePercent / 100));
    }

    double convertToSquareYards(double area) {
        return area / 9.0;
    }

    double calculateCost(double area, double costPerUnit) {
        return area * costPerUnit;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FlooringCalculator calc = new FlooringCalculator();

        System.out.println("Welcome to Damien’s Flooring Calculator!!!");
        System.out.println("--------------------------------------------------");

        System.out.print("How many rectangular sections does the room have? ");
        int sections = sc.nextInt();

        List<RoomSection> roomSections = new ArrayList<>();

        for (int i = 1; i <= sections; i++) {
            System.out.println("Section " + i + ":");
            System.out.print("  Length (ft): ");
            double length = sc.nextDouble();
            System.out.print("  Width (ft): ");
            double width = sc.nextDouble();
            roomSections.add(new RoomSection(length, width));

        }

        System.out.print("Enter waste percentage (0–30): ");
        double wastePercent = sc.nextDouble();

        System.out.println("Choose material type:");
        System.out.println("1 = Laminate");
        System.out.println("2 = Hardwood");
        System.out.println("3 = Tile");
        System.out.println("4 = Carpet");
        System.out.print("Selection: ");
        int materialChoice = sc.nextInt();

        String type = "";
        boolean usesSqYards = false;

        switch (materialChoice) {
            case 1: type = "Laminate"; break;
            case 2: type = "Hardwood"; break;
            case 3: type = "Tile"; break;
            case 4: type = "Carpet"; usesSqYards = true; break;
            default: type = "Unknown"; break;
        }

        System.out.print("Enter cost per unit (" +
                (usesSqYards ? "sq yard" : "sq ft") + "): ");
        double cost = sc.nextDouble();

        Material material = new Material(type, cost, usesSqYards);

        double totalArea = calc.calculateTotalArea(roomSections);
        double adjustedArea = calc.applyWaste(totalArea, wastePercent);

        double quantityNeeded = adjustedArea;
        if (material.usesSquareYards) {
            quantityNeeded = calc.convertToSquareYards(adjustedArea);

        sc.close();
        }

        double totalCost = calc.calculateCost(quantityNeeded, material.costPerUnit);

        System.out.println("\n================= SUMMARY =================");
        System.out.println("Material: " + material.type);
        System.out.println("Total Area (sq ft): " + Math.ceil(totalArea));
        System.out.println("Adjusted Area w/ Waste: " + Math.ceil(adjustedArea));

        if (material.usesSquareYards) {
            System.out.println("Material Needed (sq yards): " + Math.ceil(quantityNeeded));
        } else {
            System.out.println("Material Needed (sq ft): " + Math.ceil(quantityNeeded));
        }

        System.out.println("Cost per Unit: $" + material.costPerUnit);
        System.out.println("Total Cost: $" + Math.ceil(totalCost));
        System.out.println("============================================");
        System.out.println("Goodbye!");
    }
}
