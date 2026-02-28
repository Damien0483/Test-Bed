/*======================================================================
Name: Damien Harmon
Date: February, 28 2026
Assignment: SDC230L Project Flooring Calculator
Description: Represents a room where measurements are entered in feet.
No conversion is required for section dimensions.
======================================================================*/

public class FeetRoom extends Room {

    public FeetRoom(String name) {
        super(name);
    }

    public double convertLength(double value) { return value; }
    public double convertWidth(double value) { return value; }
    public String unitName() { return "Feet"; }
}
