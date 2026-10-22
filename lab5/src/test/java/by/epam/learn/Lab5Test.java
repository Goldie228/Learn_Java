package by.epam.learn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Модульные тесты классов лабораторной работы №5.
 * Запускаются на фазе {@code mvn test}.
 */
public class Lab5Test {

    @Test
    public void rectangleAreaAndPerimeter() {
        Rectangle r = new Rectangle(3, 4);
        assertEquals(12.0, r.area(), 0.0001);
        assertEquals(14.0, r.perimeter(), 0.0001);
    }

    @Test
    public void bookGetters() {
        Book b = new Book("Война и мир", "Л. Н. Толстой", 1869);
        assertEquals("Война и мир", b.getTitle());
        assertEquals("Л. Н. Толстой", b.getAuthor());
        assertEquals(1869, b.getYear());
    }

    @Test
    public void genderValues() {
        assertEquals(2, Gender.values().length);
        assertEquals(Gender.MALE, Gender.valueOf("MALE"));
        assertEquals(0, Gender.MALE.ordinal());
    }

    @Test
    public void personGender() {
        Person p = new Person("Иван", Gender.MALE);
        assertEquals("Иван", p.getName());
        assertEquals(Gender.MALE, p.getGender());
    }
}
