/**
 * Your Name
 * Date: 02/27/2026
 * Assignment: SDC230 Performance Assessment - Inheritance & Overriding
 * Description: Subclass representing a Cat that extends Animal and adds sound behavior.
 */

public class Cat extends Animal {
    private String sound;

    // Constructor
    public Cat(String name, int legs, String sound) {
        super(name, legs);
        this.sound = sound;
    }

    // Getter and Setter
    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    // Overridden print method
    @Override
    public void printAnimal() {
        System.out.println("Cat Info -> Name: " + getName() + ", Legs: " + getLegs() + ", Sound: " + sound);
    }
}
