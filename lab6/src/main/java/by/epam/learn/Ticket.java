package by.epam.learn;

/**
 * Класс "Билет" (Ticket) — демонстрация инкапсуляции.
 *
 * Задание 1, вариант 3: поля event, seatNumber, price; конструктор;
 * геттеры; метод applyDiscount().
 *
 * Все поля объявлены {@code private}, поэтому изменить их напрямую извне
 * нельзя. Чтение выполняется через геттеры, а цена меняется только
 * внутри класса — методом {@link #applyDiscount(double)}.
 */
public class Ticket {

    /** Скидка по умолчанию, % — используется в applyDiscount() без аргумента. */
    public static final double DEFAULT_DISCOUNT_PERCENT = 10.0;

    private String event;      // мероприятие
    private int seatNumber;    // номер места
    private double price;      // цена, руб.
    private int count;         // количество

    public int getCount() {
        return this.count;
    }

    public void setCount(int c) {
        this.count = c;
    }

    public Ticket (String e, int s, double p, int c) {
        this(e, s, p);

        this.count = c;
    }

    public Ticket(String event, int seatNumber, double price) {
        this.event = event;
        this.seatNumber = seatNumber;
        setPrice(price);
    }

    // --- Геттеры (доступ на чтение) ---

    public String getEvent() {
        return event;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }

    // --- Изменение состояния ---

    /** Применить скидку по умолчанию (10 %). */
    public void applyDiscount() {
        applyDiscount(DEFAULT_DISCOUNT_PERCENT);
    }

    /**
     * Применить скидку к цене билета.
     *
     * @param percent процент скидки (допустимо от 0 до 100)
     */
    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Некорректная скидка: " + percent + "%. Допустимо 0..100.");
            return;
        }
        price = price - price * percent / 100.0;
    }

    /**
     * Приватный сеттер: цена меняется только изнутри класса и с проверкой.
     * Снаружи доступен только через {@link #applyDiscount(double)}.
     */
    private void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной: " + price);
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Билет: %-15s | место %-3d | цена %.2f руб.", event, seatNumber, price);
    }
}
