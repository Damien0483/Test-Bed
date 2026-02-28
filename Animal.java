/**
 * Your Name
 * Date: 02/27/2026
 * Assignment: SDC230 Performance Assessment - Inheritance & Overriding
 * Description: Superclass representing a generic Animal with basic properties.
 */

public class Animal {
    private String name;
    private int legs;

    // Constructor
    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    // Print method
    public void printAnimal() {
        System.out.println("Animal Info -> Name: " + name + ", Legs: " + legs);
    }
}
