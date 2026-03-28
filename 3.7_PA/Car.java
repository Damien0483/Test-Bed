/**
 * Your Name
 * Date of Development
 * SDC330 Performance Assessment - Constructors and Access Specifiers
 * Description: Superclass representing a generic car with fuel and engine type.
 */

public class Car {

    private String fuel;
    private String engine;

    // Protected constructor
    protected Car(String fuel, String engine) {
        this.fuel = fuel;
        this.engine = engine;
    }

    // Public getters
    public String getFuel() {
        return fuel;
    }

    public String getEngine() {
        return engine;
    }

    // Protected setters
    protected void setFuel(String fuel) {
        this.fuel = fuel;
    }

    protected void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car Type: " + this.getClass().getSimpleName() +
               " | Fuel: " + fuel +
               " | Engine: " + engine;
    }
}
