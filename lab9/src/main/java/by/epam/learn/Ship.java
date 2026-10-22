package by.epam.learn;

/**
 * Корабль (Ship) — конкретный наследник {@link Transport}.
 *
 * Доставляет груз по воде, время считает как расстояние / (скорость * 0.8):
 * коэффициент 0.8 учитывает потери скорости на воде.
 */
public class Ship extends Transport {

    private double tonnage;

    public Ship(double capacity, double speed, double fuel, double tonnage) {
        super(capacity, speed, fuel);
        this.tonnage = tonnage;
    }

    public double getTonnage() {
        return tonnage;
    }

    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
    }

    @Override
    public String deliver() {
        return "Доставляет груз по воде";
    }

    @Override
    public double calculateTime(double distance) {
        checkDistance(distance);
        return distance / (getSpeed() * 0.8);
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + String.format(", тоннаж: %.1f т", tonnage);
    }
}
