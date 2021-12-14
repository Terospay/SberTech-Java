package garage;

import java.util.*;

public class Garage implements GarageInterface {
    private final ArrayList<Car> cars;
    private final ArrayList<Owner> owners;
    private final ArrayList<String> brands;
    private final HashMap<String, ArrayList<Car>> brandToCars;
    private final HashMap<Owner, ArrayList<Car>> ownerToCars;
    private final HashMap<String, ArrayList<Owner>> brandToOwners;

    Garage() {
        cars = new ArrayList<>();
        owners = new ArrayList<>();
        brands = new ArrayList<String>();
        brandToCars = new HashMap<>();
        ownerToCars = new HashMap<>();
        brandToOwners = new HashMap<>();
    }


    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return owners;
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        List<Integer> pos = new ArrayList<>(Arrays.asList(new Integer[3]));
        List<Integer> value = new ArrayList<>(Arrays.asList(new Integer[3]));
        for (int i = 0; i < 3; ++i) {
            pos.set(i, 0);
            value.set(i, -1);
        }


        for (int i = 0; i < cars.size(); ++i) {
            int maxVelocity = cars.get(i).getMaxVelocity();
            if (maxVelocity > value.get(0)) {
                value.set(2, value.get(1));
                pos.set(2, pos.get(1));
                value.set(1, value.get(0));
                pos.set(1, pos.get(0));
                value.set(0, maxVelocity);
                pos.set(0, i);
            } else if (maxVelocity > value.get(1)) {
                value.set(2, value.get(1));
                pos.set(2, pos.get(1));
                value.set(1, maxVelocity);
                pos.set(1, i);
            } else if (maxVelocity > value.get(2)) {
                value.set(2, maxVelocity);
                pos.set(2, i);
            }
        }

        List<Car> returnable = new ArrayList<>(3);
        for (int i = 0; i < 3; ++i) {
            if (value.get(i) != -1) {
                returnable.add(cars.get(pos.get(i)));
            }
        }
        return returnable;
    }

    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        return brandToCars.get(brand);
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        List<Car> returnable = new ArrayList<>();
        for (Car c : cars) {
            if (c.getPower() > power) {
                returnable.add(c);
            }
        }
        return returnable;
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        return ownerToCars.get(owner);
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        int sumAge = 0;
        ArrayList<Owner> ownersForBrand = brandToOwners.get(brand);
        for (Owner owner : ownersForBrand) {
            sumAge += owner.getAge();
        }
        return sumAge / ownersForBrand.size();
    }

    @Override
    public int meanCarNumberForEachOwner() {
        return cars.size();
    }

    @Override
    public Car removeCar(int carId) {
        Car removable = null;
        for (Car c : cars) {
            if (c.getCarId() == carId) {
                removable = c;
                break;
            }
        }
        if (removable != null) {
            cars.remove(removable);
            brandToCars.get(removable.getBrand()).remove(removable);
            Owner ownerThisCar = null;
            for (Owner o : owners) {
                if (o.getOwnerId() == removable.getOwnerId()) {
                    ownerThisCar = o;
                }
            }
            ownerToCars.get(ownerThisCar).remove(removable);
        }
        return removable;
    }

    @Override
    public void addNewCar(Car car, Owner owner) {
        boolean[] exist = chechExistence(car, owner);
        boolean carExist = exist[0];
        boolean brandExist = exist[1];
        boolean ownerExist = exist[2];
        if (!brandExist) {
            brands.add(car.getBrand());
            brandToOwners.put(car.getBrand(), new ArrayList<>());
            brandToCars.put(car.getBrand(), new ArrayList<>());
        }
        if (!ownerExist) {
            owners.add(owner);
            ownerToCars.put(owner, new ArrayList<>());
        }
        if (!carExist) {
            cars.add(car);
            ownerToCars.get(owner).add(car);
            brandToCars.get(car.getBrand()).add(car);
        }
        boolean ownerInBrandExist = false;
        if (brandExist && ownerExist) {
            for (Owner o : brandToOwners.get(car.getBrand())) {
                if (o.equals(owner)) {
                    ownerInBrandExist = true;
                    break;
                }
            }
        }
        if (!brandExist || !ownerExist || !ownerInBrandExist) {
            brandToOwners.get(car.getBrand()).add(owner);
        }
    }

    private boolean[] chechExistence(Car car, Owner owner) {
        boolean[] exist = {false, false, false};
        for (Car c : cars) {
            if (c.equals(car)) {
                exist[0] = true;
            }
            if (c.getBrand().equals(car.getBrand())) {
                exist[1] = true;
                break;
            }
        }
        for (Owner o : owners) {
            if (o.equals(owner)) {
                exist[2] = true;
                break;
            }
        }
        return exist;
    }
}
