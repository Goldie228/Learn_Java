package by.epam.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

/**
 * Модульные тесты абстрактного класса Transport, его наследников Truck / Ship
 * и примера Animal / Dog (лабораторная работа №9).
 */
public class Lab9Test {

    @Test
    public void truckCalculatesTimeBySpeed() {
        Truck truck = new Truck(20.0, 80.0, 300.0, 15.0);

        assertEquals(6.25, truck.calculateTime(500.0), 0.0001);
        assertEquals(1.25, truck.calculateTime(100.0), 0.0001);
    }

    @Test
    public void shipCalculatesTimeWithWaterCoefficient() {
        Ship ship = new Ship(500.0, 80.0, 1000.0, 300.0);

        assertEquals(7.8125, ship.calculateTime(500.0), 0.0001);
        assertEquals(15.625, ship.calculateTime(1000.0), 0.0001);
    }

    @Test
    public void zeroDistanceGivesZeroTime() {
        Transport truck = new Truck(20.0, 80.0, 300.0, 15.0);
        Transport ship = new Ship(500.0, 80.0, 1000.0, 300.0);

        assertEquals(0.0, truck.calculateTime(0.0), 0.0001);
        assertEquals(0.0, ship.calculateTime(0.0), 0.0001);
    }

    @Test
    public void deliverMessagesDifferByTransportType() {
        Transport truck = new Truck(20.0, 80.0, 300.0, 15.0);
        Transport ship = new Ship(500.0, 80.0, 1000.0, 300.0);

        assertEquals("Доставляет груз по суше", truck.deliver());
        assertEquals("Доставляет груз по воде", ship.deliver());
        assertFalse(truck.deliver().equals(ship.deliver()));
    }

    @Test
    public void displayInfoContainsCommonAndUniqueFields() {
        Truck truck = new Truck(20.0, 80.0, 300.0, 15.0);
        Ship ship = new Ship(500.0, 80.0, 1000.0, 300.0);

        assertTrue(truck.displayInfo().contains("20"));
        assertTrue(truck.displayInfo().contains("80"));
        assertTrue(truck.displayInfo().contains("300"));
        assertTrue(truck.displayInfo().contains("вес груза"));
        assertFalse(truck.displayInfo().contains("тоннаж"));

        assertTrue(ship.displayInfo().contains("500"));
        assertTrue(ship.displayInfo().contains("тоннаж"));
        assertFalse(ship.displayInfo().contains("вес груза"));
    }

    @Test
    public void negativeDistanceThrowsIllegalArgument() {
        Transport truck = new Truck(20.0, 80.0, 300.0, 15.0);
        Transport ship = new Ship(500.0, 80.0, 1000.0, 300.0);

        try {
            truck.calculateTime(-1.0);
            fail("Truck: ожидалось IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Расстояние"));
        }

        try {
            ship.calculateTime(-0.5);
            fail("Ship: ожидалось IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Расстояние"));
        }
    }

    @Test
    public void nonPositiveSpeedThrowsIllegalArgument() {
        try {
            new Truck(20.0, 0.0, 300.0, 15.0);
            fail("Ожидалось IllegalArgumentException при speed = 0");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Скорость"));
        }

        try {
            new Ship(500.0, -10.0, 1000.0, 300.0);
            fail("Ожидалось IllegalArgumentException при speed < 0");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Скорость"));
        }
    }

    @Test
    public void setSpeedRejectsNonPositiveValue() {
        Transport truck = new Truck(20.0, 80.0, 300.0, 15.0);

        try {
            truck.setSpeed(0.0);
            fail("Ожидалось IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(80.0, truck.getSpeed(), 0.0001);
        }

        truck.setSpeed(100.0);
        assertEquals(100.0, truck.getSpeed(), 0.0001);
    }

    @Test
    public void polymorphicArrayHasThreeTrucksAndTwoShips() {
        Transport[] transports = {
            new Truck(20.0, 80.0, 300.0, 15.0),
            new Truck(10.0, 60.0, 150.0, 8.0),
            new Truck(25.0, 90.0, 400.0, 20.0),
            new Ship(500.0, 80.0, 1000.0, 300.0),
            new Ship(750.0, 40.0, 1500.0, 450.0)
        };

        int truckCount = 0;
        int shipCount = 0;
        for (Transport transport : transports) {
            if (transport instanceof Truck) {
                truckCount++;
            } else if (transport instanceof Ship) {
                shipCount++;
            }
            assertTrue(transport instanceof Transport);
        }

        assertEquals(5, transports.length);
        assertEquals(3, truckCount);
        assertEquals(2, shipCount);
        assertEquals(6.25, transports[0].calculateTime(500.0), 0.0001);
        assertEquals(7.8125, transports[3].calculateTime(500.0), 0.0001);
    }

    @Test
    public void dogIsAnimalAndOverridesSound() {
        Dog dog = new Dog("Шарик", 3, "Овчарка");

        assertTrue(dog instanceof Animal);
        assertTrue(dog instanceof Dog);
        assertEquals("Шарик", dog.getName());
        assertEquals(3, dog.getAge());
        assertEquals("Овчарка", dog.getBreed());
        assertEquals("Гав-гав!", dog.makeSound());

        Animal animal = dog;
        assertEquals("Гав-гав!", animal.makeSound());
    }

    @Test
    public void animalConstructorRunsBeforeDogConstructor() {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        try {
            new Dog("Шарик", 3, "Овчарка");
        } finally {
            System.setOut(original);
        }

        String output = buffer.toString();
        int animalIndex = output.indexOf("Конструктор Animal вызван");
        int dogIndex = output.indexOf("Конструктор Dog вызван");

        assertTrue("Сообщение Animal должно присутствовать", animalIndex >= 0);
        assertTrue("Сообщение Dog должно присутствовать", dogIndex >= 0);
        assertTrue("Сначала вызывается конструктор Animal", animalIndex < dogIndex);
    }

    @Test
    public void uniqueFieldsHaveGettersAndSetters() {
        Truck truck = new Truck(20.0, 80.0, 300.0, 15.0);
        Ship ship = new Ship(500.0, 80.0, 1000.0, 300.0);

        truck.setCargoWeight(18.5);
        ship.setTonnage(420.0);

        assertEquals(18.5, truck.getCargoWeight(), 0.0001);
        assertEquals(420.0, ship.getTonnage(), 0.0001);
        assertEquals(20.0, truck.getCapacity(), 0.0001);
        assertEquals(300.0, truck.getFuel(), 0.0001);
    }
}
