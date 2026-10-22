package by.epam.learn;

/**
 * Абстрактный класс "Транспорт" (Transport) — вариант 9 лабораторной работы №9.
 *
 * Задаёт общий контракт для перевозки грузов: абстрактные методы
 * {@link #deliver()} и {@link #calculateTime(double)}, а также конкретный
 * метод {@link #displayInfo()} с общими характеристиками.
 *
 * Объект абстрактного класса создать нельзя: {@code new Transport(...)}
 * не компилируется. Можно объявить ссылку типа Transport и присвоить ей
 * объект наследника.
 */
public abstract class Transport {

    private double capacity;
    private double speed;
    private double fuel;

    /**
     * @param capacity вместимость, т
     * @param speed    скорость, км/ч (должна быть больше нуля)
     * @param fuel     запас топлива, л
     * @throws IllegalArgumentException если скорость не положительна
     */
    public Transport(double capacity, double speed, double fuel) {
        if (speed <= 0) {
            throw new IllegalArgumentException("Скорость должна быть больше нуля");
        }
        this.capacity = capacity;
        this.speed = speed;
        this.fuel = fuel;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if (speed <= 0) {
            throw new IllegalArgumentException("Скорость должна быть больше нуля");
        }
        this.speed = speed;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    /** Доставка груза — реализуется в каждом наследнике по-своему. */
    public abstract String deliver();

    /**
     * Время доставки на заданное расстояние, ч.
     *
     * @param distance расстояние, км (не может быть отрицательным)
     * @return время в часах
     * @throws IllegalArgumentException если расстояние отрицательное
     */
    public abstract double calculateTime(double distance);

    /** Строка с общими характеристиками транспорта. */
    public String displayInfo() {
        return String.format("Вместимость: %.1f т, скорость: %.1f км/ч, топливо: %.1f л",
                capacity, speed, fuel);
    }

    /**
     * Общий помощник наследников: проверка расстояния.
     *
     * @throws IllegalArgumentException если расстояние отрицательное
     */
    protected static void checkDistance(double distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Расстояние не может быть отрицательным");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + displayInfo() + "}";
    }
}
