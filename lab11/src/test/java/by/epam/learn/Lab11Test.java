package by.epam.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Модульные тесты перегрузки, переопределения и приведения типов
 * (лабораторная работа №11).
 */
public class Lab11Test {

    @Test
    public void runWithoutArguments() {
        Computer computer = new Computer("Base PC", 8, 256);

        assertEquals("Компьютер работает", computer.run());
    }

    @Test
    public void runWithProgramName() {
        Computer computer = new Computer("Base PC", 8, 256);

        assertEquals("Запускает Word", computer.run("Word"));
    }

    @Test
    public void runWithProcessCount() {
        Computer computer = new Computer("Base PC", 8, 256);

        assertEquals("Запускает 12 процессов", computer.run(12));
    }

    @Test
    public void overloadsReturnDifferentResults() {
        Computer computer = new Computer("Base PC", 8, 256);

        String noArgs = computer.run();
        String withProgram = computer.run("Word");
        String withCount = computer.run(12);

        assertFalse(noArgs.equals(withProgram));
        assertFalse(noArgs.equals(withCount));
        assertFalse(withProgram.equals(withCount));
    }

    @Test
    public void performanceIsOverriddenThroughComputerReference() {
        Computer gaming = new GamingPC("Aurora X", 32, 2000, "NVIDIA RTX 4080");
        Computer office = new OfficePC("Office Line 1", 8, 256, 23.8);
        Computer base = new Computer("Base PC", 8, 256);

        assertEquals("Высокая", gaming.getPerformance());
        assertEquals("Средняя", office.getPerformance());
        assertEquals("Средняя", base.getPerformance());
    }

    @Test
    public void polymorphicListDispatchesToOverriddenMethods() {
        List<Computer> computers = new ArrayList<>();
        computers.add(new GamingPC("Aurora X", 32, 2000, "NVIDIA RTX 4080"));
        computers.add(new GamingPC("Titan Pro", 16, 1000, "AMD Radeon RX 7800 XT"));
        computers.add(new GamingPC("Vortex Mini", 16, 512, "NVIDIA RTX 4060"));
        computers.add(new OfficePC("Office Line 1", 8, 256, 23.8));
        computers.add(new OfficePC("Office Line 2", 16, 512, 27.0));

        int gamingCount = 0;
        int officeCount = 0;
        for (Computer computer : computers) {
            if (computer instanceof GamingPC) {
                gamingCount++;
                assertEquals("Высокая", computer.getPerformance());
                assertTrue(computer.displayInfo().contains("видеокарта"));
            } else if (computer instanceof OfficePC) {
                officeCount++;
                assertEquals("Средняя", computer.getPerformance());
                assertTrue(computer.displayInfo().contains("монитор"));
            }
        }

        assertEquals(5, computers.size());
        assertEquals(3, gamingCount);
        assertEquals(2, officeCount);
    }

    @Test
    public void instanceofAllowsSafeDowncastingAndOwnOverloads() {
        Computer gamingComputer = new GamingPC("Aurora X", 32, 2000, "NVIDIA RTX 4080");
        Computer officeComputer = new OfficePC("Office Line 1", 8, 256, 23.8);

        assertTrue(gamingComputer instanceof GamingPC);
        GamingPC gaming = (GamingPC) gamingComputer;
        assertEquals("Запускает игру Cyberpunk 2077 с 120 FPS",
                gaming.run("Cyberpunk 2077", 120));

        assertTrue(officeComputer instanceof OfficePC);
        OfficePC office = (OfficePC) officeComputer;
        assertEquals("Запускает Excel в свёрнутом окне", office.run("Excel", true));
        assertEquals("Запускает Excel в обычном окне", office.run("Excel", false));

        assertFalse(officeComputer instanceof GamingPC);
    }

    @Test
    public void displayInfoContainsChildFieldAndBaseData() {
        Computer gaming = new GamingPC("Aurora X", 32, 2000, "NVIDIA RTX 4080");
        Computer office = new OfficePC("Office Line 1", 8, 256, 23.8);

        String gamingInfo = gaming.displayInfo();
        assertTrue(gamingInfo.contains("Aurora X"));
        assertTrue(gamingInfo.contains("32 ГБ"));
        assertTrue(gamingInfo.contains("NVIDIA RTX 4080"));
        assertTrue(gamingInfo.contains("Высокая"));

        String officeInfo = office.displayInfo();
        assertTrue(officeInfo.contains("Office Line 1"));
        assertTrue(officeInfo.contains("8 ГБ"));
        assertTrue(officeInfo.contains("23.8 дюймов"));
        assertTrue(officeInfo.contains("Средняя"));
    }
}
