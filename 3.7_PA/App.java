/**
 * Your Name
 * Date of Development
 * SDC330 Performance Assessment - Constructors and Access Specifiers
 * Description: Main application demonstrating constructors, access specifiers,
 *              inheritance, and composition using Car, GasCar, ElectricCar,
 *              and ParkingLot classes.
 */

public class App {

    public static void main(String[] args) {

        System.out.println("Your Name - Week 3 Constructors and Access Specifiers Performance Assessment");

        // Create ParkingLot instance
        ParkingLot lot = new ParkingLot();

        // Create ElectricCar instance
        ElectricCar eCar = new ElectricCar();
        lot.addCar(eCar);

        // Create GasCar using 2-parameter constructor
        GasCar gasCar1 = new GasCar("Gasoline", "V6");
        lot.addCar(gasCar1);

        // Create GasCar using 1-parameter constructor
        GasCar gasCar2 = new GasCar("V8");
        lot.addCar(gasCar2);

        // Print header
        System.out.println("\nCars in the Parking Lot:");
        System.out.println("-------------------------");

        // Print all cars
        for (Car c : lot.getCars()) {
            System.out.println(c);
        }
    }
}
