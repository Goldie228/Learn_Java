package by.epam.learn;

/**
 * Грузовик (Truck) — конкретный наследник {@link Transport}.
 *
 * Доставляет груз по суше, время считает как расстояние / скорость.
 */
public class Truck extends Transport {

    private double cargoWeight;

    public Truck(double capacity, double speed, double fuel, double cargoWeight) {
        super(capacity, speed, fuel);
        this.cargoWeight = cargoWeight;
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    @Override
    public String deliver() {
        return "Доставляет груз по суше";
    }

    @Override
    public double calculateTime(double distance) {
        checkDistance(distance);
        return distance / getSpeed();
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + String.format(", вес груза: %.1f т", cargoWeight);
    }
}
