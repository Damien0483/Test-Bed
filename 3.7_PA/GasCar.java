/**
 * Your Name
 * Date of Development
 * SDC330 Performance Assessment - Constructors and Access Specifiers
 * Description: Gas-powered car class demonstrating constructor overloading.
 */

public class GasCar extends Car {

    // 2-parameter constructor
    public GasCar(String fuel, String engine) {
        super(fuel, engine);
    }

    // 1-parameter constructor (default fuel)
    public GasCar(String engine) {
        super("Gasoline", engine);
    }

    // Public method to update fuel type
    public void updateFuel(String newFuel) {
        setFuel(newFuel);
    }
}
