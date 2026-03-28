import java.util.ArrayList;

/**
 * Your Name
 * Date of Development
 * SDC330 Performance Assessment - Constructors and Access Specifiers
 * Description: Parking lot class composed of Car objects.
 */

public class ParkingLot {

    private ArrayList<Car> cars = new ArrayList<>();

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
