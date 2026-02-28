/*======================================================================
Name: Damien Harmon
Date: February, 28 2026
Assignment: SDC230L Project Flooring Calculator
Description: Stores material type, cost, and whether the material uses
square yards instead of square feet.
======================================================================*/
public class Material {

    public String type;
    public double costPerUnit;
    public boolean usesSquareYards;

    //Constructor: Creates a material definition.
    public Material(String type, double costPerUnit, boolean usesSquareYards) {
        this.type = type;
        this.costPerUnit = costPerUnit;
        this.usesSquareYards = usesSquareYards;
    }
}
