package by.epam.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Модульные тесты класса {@link Ticket} (лабораторная работа №6).
 * Запускаются на фазе {@code mvn test}.
 */
public class Lab6Test {

    @Test
    public void constructorAndGetters() {
        Ticket t = new Ticket("Концерт", 42, 100.0);
        assertEquals("Концерт", t.getEvent());
        assertEquals(42, t.getSeatNumber());
        assertEquals(100.0, t.getPrice(), 0.0001);
    }

    @Test
    public void applyDiscountWithPercent() {
        Ticket t = new Ticket("Концерт", 42, 100.0);
        t.applyDiscount(25);
        assertEquals(75.0, t.getPrice(), 0.0001);
    }

    @Test
    public void applyDefaultDiscount() {
        Ticket t = new Ticket("Концерт", 42, 200.0);
        t.applyDiscount();
        assertEquals(180.0, t.getPrice(), 0.0001);
    }

    @Test
    public void invalidDiscountIsIgnored() {
        Ticket t = new Ticket("Концерт", 42, 100.0);
        t.applyDiscount(150);
        assertEquals(100.0, t.getPrice(), 0.0001);
    }

    @Test
    public void negativePriceRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Ticket("Концерт", 42, -10.0));
    }
}
