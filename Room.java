/*======================================================================
Name: Damien Harmon
Date: February, 28 2026
Assignment: SDC230L Project Flooring Calculator
Description: Abstract base class representing a room. Supports multiple
sections, waste percentage, material selection, and summary reporting. 
Subclasses define unit conversion behavior.
======================================================================*/
import java.util.ArrayList;
import java.util.List;

public abstract class Room {

    protected String name;
    protected List<RoomSection> sections = new ArrayList<>();
    protected double wastePercent;
    protected Material material;

    //Constructor: Creates a room with a given name.
    public Room(String name) {
        this.name = name;
    }

    // Abstract conversion methods implemented by subclasses.
    public abstract double convertLength(double value);
    public abstract double convertWidth(double value);
    public abstract String unitName();

    //Adds a rectangular section to the room.
    public void addSection(double length, double width) {
        sections.add(new RoomSection(convertLength(length), convertWidth(width)));
    }

    //Sets the waste percentage for this room.
    public void setWastePercent(double wastePercent) {
        this.wastePercent = wastePercent;
    }

    //Assigns a material to the room.
    public void setMaterial(Material material) {
        this.material = material;
    }

    //Calculates total area of all sections.
    public double totalArea() {
        double total = 0;
        for (RoomSection s : sections) {
            total += s.getArea();
        }
        return total;
    }

    //Calculates area including waste.
    public double adjustedArea() {
        return totalArea() * (1 + wastePercent / 100.0);
    }

    //Calculates material quantity needed.
    public double materialQuantity() {
        if (material.usesSquareYards) {
            return adjustedArea() / 9.0;
        }
        return adjustedArea();
    }

    //Calculates total cost of material.
    public double totalCost() {
        return materialQuantity() * material.costPerUnit;
    }

    //Prints a detailed summary of the room.
    public void printSummary() {
        System.out.println("\n================= SUMMARY FOR ROOM: " + name + " =================");
        System.out.println("Units Entered In: " + unitName());
        System.out.println("Material: " + material.type);

        double areaFt = totalArea();
        double areaIn = totalArea() * 144;

        System.out.println("Total Area (sq ft): " + Math.ceil(areaFt));
        System.out.println("Total Area (sq in): " + Math.ceil(areaIn));

        double adjFt = adjustedArea();
        double adjIn = adjustedArea() * 144;

        System.out.println("Adjusted Area w/ Waste (sq ft): " + Math.ceil(adjFt));
        System.out.println("Adjusted Area w/ Waste (sq in): " + Math.ceil(adjIn));

        if (material.usesSquareYards) {
            System.out.println("Material Needed (sq yards): " + Math.ceil(materialQuantity()));
        } else {
            System.out.println("Material Needed (sq ft): " + Math.ceil(materialQuantity()));
        }

        System.out.println("Cost per Unit: $" + material.costPerUnit);
        System.out.println("Total Cost: $" + Math.ceil(totalCost()));
        System.out.println("===============================================================");
    }
}
