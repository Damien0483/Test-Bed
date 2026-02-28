/**
 * Your Name
 * Date: 02/27/2026
 * Assignment: SDC230 Performance Assessment - Inheritance & Overriding
 * Description: Main application class that tests Animal and Cat classes.
 */

public class App {
    public static void main(String[] args) {

        System.out.println("Your Name - Week 5 PA Inheritance and Overriding.\n");

        // Create instances
        Animal a1 = new Animal("Buddy", 4);
        Cat c1 = new Cat("Whiskers", 4, "Meow");

        // Print original values
        System.out.println("Original Values:");
        a1.printAnimal();
        c1.printAnimal();

        System.out.println("\nUpdated Values:");

        // Update values
        a1.setName("Max");
        a1.setLegs(3);

        c1.setName("Shadow");
        c1.setLegs(4);
        c1.setSound("Purr");

        // Print updated values
        a1.printAnimal();
        c1.printAnimal();
    }
}
