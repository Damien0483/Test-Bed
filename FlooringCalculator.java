/*======================================================================
Name: Damien Harmon
Date: February, 28 2026
Assignment: SDC230L Project Flooring Calculator
Description: Utility class for performing flooring calculations such as
waste adjustment, unit conversion, and cost computation.
======================================================================*/

public class FlooringCalculator {

    //Applies waste percentage to an area.
    public double applyWaste(double area, double wastePercent) {
        return area * (1 + wastePercent / 100.0);
    }

    //Converts square feet to square yards.
    public double convertToSquareYards(double areaSqFt) {
        return areaSqFt / 9.0;
    }

    //Calculates total cost.
    public double calculateCost(double quantity, double costPerUnit) {
        return quantity * costPerUnit;
    }
}
