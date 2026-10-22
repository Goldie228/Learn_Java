package by.epam.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Модульные тесты наследования классов Computer / GamingPC / OfficePC
 * и цепочки Point1D → Point2D → Point3D (лабораторная работа №8).
 */
public class Lab8Test {

    @Test
    public void computerGettersAndSetters() {
        Computer computer = new Computer("Intel Core i3", 8, 256);

        assertEquals("Intel Core i3", computer.getProcessor());
        assertEquals(8, computer.getRam());
        assertEquals(256, computer.getStorage());

        computer.setProcessor("AMD Ryzen 3");
        computer.setRam(16);
        computer.setStorage(512);

        assertEquals("AMD Ryzen 3", computer.getProcessor());
        assertEquals(16, computer.getRam());
        assertEquals(512, computer.getStorage());
    }

    @Test
    public void inheritanceIsAvailableThroughBaseReference() {
        Computer gaming = new GamingPC("Intel Core i9", 32, 2000, "RTX 4080");
        Computer office = new OfficePC("Intel Core i5", 8, 256, 21.5);

        assertEquals("Intel Core i9", gaming.getProcessor());
        assertEquals(32, gaming.getRam());
        assertEquals(2000, gaming.getStorage());

        assertEquals("Intel Core i5", office.getProcessor());
        assertEquals(8, office.getRam());
        assertEquals(256, office.getStorage());
    }

    @Test
    public void basePerformanceIsDefault() {
        Computer computer = new Computer("Intel Core i3", 4, 128);

        assertEquals("Базовая производительность", computer.getPerformance());
    }

    @Test
    public void performanceIsOverriddenInBothSubclasses() {
        Computer gaming = new GamingPC("Intel Core i9", 32, 2000, "RTX 4080");
        Computer office = new OfficePC("Intel Core i5", 8, 256, 21.5);

        assertEquals("Высокая производительность", gaming.getPerformance());
        assertEquals("Средняя производительность", office.getPerformance());
        assertNotEquals(gaming.getPerformance(), office.getPerformance());
        assertNotEquals(new Computer("Intel Core i3", 4, 128).getPerformance(),
                gaming.getPerformance());
    }

    @Test
    public void displayInfoContainsBaseAndUniqueFields() {
        GamingPC gaming = new GamingPC("Intel Core i9", 32, 2000, "RTX 4080");
        OfficePC office = new OfficePC("Intel Core i5", 8, 256, 27.0);

        assertTrue(gaming.displayInfo().contains("Intel Core i9"));
        assertTrue(gaming.displayInfo().contains("32 ГБ"));
        assertTrue(gaming.displayInfo().contains("RTX 4080"));

        assertTrue(office.displayInfo().contains("Intel Core i5"));
        assertTrue(office.displayInfo().contains("дюймов"));
        assertFalse(office.displayInfo().contains("RTX 4080"));
    }

    @Test
    public void uniqueMethodsAreCalledAfterInstanceofCast() {
        Computer[] computers = {
            new GamingPC("Intel Core i9", 32, 2000, "RTX 4080"),
            new OfficePC("Intel Core i5", 8, 256, 21.5)
        };

        assertEquals("Запускает современную игру", gamingAction(computers[0]));
        assertEquals("Печатает документ", officeAction(computers[1]));
        assertTrue(computers[0] instanceof Computer);
        assertFalse(computers[1] instanceof GamingPC);
    }

    private String gamingAction(Computer computer) {
        if (computer instanceof GamingPC gaming) {
            return gaming.playGame();
        }
        return "не игровой компьютер";
    }

    private String officeAction(Computer computer) {
        if (computer instanceof OfficePC office) {
            return office.printDocument();
        }
        return "не офисный компьютер";
    }

    @Test
    public void arrayOfFiveComputersHasThreeGamingAndTwoOffice() {
        Computer[] computers = new Computer[] {
            new GamingPC("Intel Core i9", 32, 2000, "RTX 4080"),
            new GamingPC("AMD Ryzen 7", 16, 1000, "RTX 3070"),
            new GamingPC("Intel Core i7", 16, 512, "RX 6700"),
            new OfficePC("Intel Core i5", 8, 256, 21.5),
            new OfficePC("AMD Ryzen 5", 16, 512, 27.0)
        };

        int gamingCount = 0;
        int officeCount = 0;
        for (Computer computer : computers) {
            if (computer instanceof GamingPC) {
                gamingCount++;
            } else if (computer instanceof OfficePC) {
                officeCount++;
            }
        }

        assertEquals(5, computers.length);
        assertEquals(3, gamingCount);
        assertEquals(2, officeCount);
        assertEquals("Высокая производительность", computers[0].getPerformance());
        assertEquals("Средняя производительность", computers[4].getPerformance());
    }

    @Test
    public void pointChainStoresAllCoordinates() {
        Point3D point = new Point3D(4, 5, 6);

        assertEquals(4, point.getX());
        assertEquals(5, point.getY());
        assertEquals(6, point.getZ());
        assertTrue(point instanceof Point2D);
        assertTrue(point instanceof Point1D);
        assertFalse(new Point2D(1, 2) instanceof Point3D);
        assertEquals("Point3D{x=4, y=5, z=6}", point.toString());
    }

    @Test
    public void point3dDefaultConstructorUsesMinusOne() {
        Point3D point = new Point3D();

        assertEquals(-1, point.getX());
        assertEquals(-1, point.getY());
        assertEquals(-1, point.getZ());
        assertEquals("Point3D{x=-1, y=-1, z=-1}", point.toString());
    }

    @Test
    public void point2dInitializesOnlyOwnAndParentFields() {
        Point2D point = new Point2D(7, 8);

        assertEquals(7, point.getX());
        assertEquals(8, point.getY());
        assertEquals("Point2D{x=7, y=8}", point.toString());
    }
}
