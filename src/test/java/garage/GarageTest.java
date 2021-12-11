package garage;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class GarageTest {

    private static final Owner o1 = new Owner("Alex", "Ivanov", 20, 0);
    private static final Owner o2 = new Owner("Alex", "Ivanov", 30, 1);
    private static final Owner o3 = new Owner("Alex", "Ivanov", 40, 2);
    private static final Car c1 = new Car(0, "Toyota", "Camry", 200, 350, 0);
    private static final Car c2 = new Car(1, "Porsche", "Panamera", 300, 500, 1);
    private static final Car c3 = new Car(2, "Ferrari", "Roma", 200, 350, 2);
    private static final Car c4 = new Car(3, "Ferrari", "FF", 350, 480, 1);
    private static Garage garage = new Garage();

    @BeforeClass
    public static void init() {
        garage.addNewCar(c1, o1);
        garage.addNewCar(c2, o2);
        garage.addNewCar(c3, o3);
        garage.addNewCar(c4, o2);
    }

    @Test
    public void allCarsUniqueOwners() {
        List<Owner> expected = new ArrayList<Owner>(Arrays.asList(o1, o2, o3));
        assertEquals(garage.allCarsUniqueOwners(), expected);
    }

    @Test
    public void topThreeCarsByMaxVelocity() {
        List<Car> expected = new ArrayList<Car>(Arrays.asList(c4, c2, c1));
        assertEquals(garage.topThreeCarsByMaxVelocity(), expected);

    }

    @Test
    public void allCarsOfBrand() {
        List<Car> expected = new ArrayList<Car>(Arrays.asList(c3, c4));
        assertEquals(garage.allCarsOfBrand("Ferrari"), expected);
    }

    @Test
    public void carsWithPowerMoreThan() {
        List<Car> expected = new ArrayList<Car>(Arrays.asList(c2, c4));
        assertEquals(garage.carsWithPowerMoreThan(400), expected);
    }

    @Test
    public void allCarsOfOwner() {
        List<Car> expected = new ArrayList<Car>(Arrays.asList(c2, c4));
        assertEquals(garage.allCarsOfOwner(o2), expected);
    }

    @Test
    public void meanOwnersAgeOfCarBrand() {
        assertEquals(35, garage.meanOwnersAgeOfCarBrand("Ferrari"));
    }

    @Test
    public void meanCarNumberForEachOwner() {
        assertEquals(4, garage.meanCarNumberForEachOwner());
    }

    @Test
    public void removeCar() {
        assertEquals(c1, garage.removeCar(0));
    }

}