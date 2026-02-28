/*========================================================================
Name: Damien Harmon
Date: February, 28 2026
Assignment: SDC230L Project Flooring Calculator
Description: Represents a rectangular section of a room. Stores length
and width (in feet after conversion) and calculates area.
=========================================================================*/
public class RoomSection {

    private double length;
    private double width;

    //Constructor: Creates a room section with given dimensions.
    public RoomSection(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    //Calculates the area of this section and returns the value in sq feet.
    public double getArea() {
        return length * width;
    }
}
