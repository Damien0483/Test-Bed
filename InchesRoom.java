/*======================================================================
Name: Damien Harmon
Date: February, 28 2026
Assignment: SDC230L Project Flooring Calculator
Description: Represents a room where measurements are entered in inches.
Converts inches to feet for internal calculations.
======================================================================*/
public class InchesRoom extends Room {

    public InchesRoom(String name) {
        super(name);
    }

    public double convertLength(double value) { return value / 12.0; }
    public double convertWidth(double value) { return value / 12.0; }
    public String unitName() { return "Inches"; }
}
