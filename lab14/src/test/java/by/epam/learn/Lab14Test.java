package by.epam.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Модульные тесты файловых операций (лабораторная работа №14).
 * Файлы создаются во временном каталоге JUnit.
 */
public class Lab14Test {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private final FileTaskService service = new FileTaskService();

    @Test
    public void createUniversityFileHasExpectedContent() throws Exception {
        Path dir = folder.newFolder("task1").toPath();

        Path file = service.createUniversityFile(dir);

        assertTrue(Files.exists(file));
        assertEquals("university.txt", file.getFileName().toString());
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        assertTrue(lines.get(0).contains("Колледж бизнеса и права"));
        assertTrue(lines.contains("Город: Минск"));
        assertTrue(lines.stream().anyMatch(line -> line.contains("Право")));
        assertTrue(lines.stream().anyMatch(line -> line.contains("Экономика")));
    }

    @Test
    public void printFileInfoPrintsMainCharacteristics() throws Exception {
        Path file = service.createUniversityFile(folder.newFolder("info").toPath());
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream original = System.out;

        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            service.printFileInfo(file);
        } finally {
            System.setOut(original);
        }

        String text = buffer.toString(StandardCharsets.UTF_8);
        assertTrue(Files.exists(file));
        assertTrue(text.contains("Имя файла: university.txt"));
        assertTrue(text.contains("Размер:"));
        assertTrue(text.contains("Существует: да"));
        assertTrue(text.contains("Это файл: да"));
        assertTrue(text.contains("Это каталог: нет"));
    }

    @Test
    public void writeAndReadIntsPreserveOrderAndValues() throws Exception {
        Path file = folder.newFile("ints.txt").toPath();
        List<Integer> values = List.of(5, -2, 0, 17, -100);

        service.writeInts(file, values);

        assertEquals(values, service.readInts(file));
    }

    @Test
    public void differenceMaxMinForOrdinaryValues() {
        assertEquals(29, service.differenceMaxMin(List.of(10, -4, 25)));
        assertEquals(0, service.differenceMaxMin(List.of(7)));
    }

    @Test
    public void differenceMaxMinForNegativeValues() {
        assertEquals(7, service.differenceMaxMin(List.of(-10, -3, -7)));
    }

    @Test
    public void differenceMaxMinRejectsEmptyList() {
        assertThrows(IllegalArgumentException.class,
                () -> service.differenceMaxMin(List.of()));
    }

    @Test
    public void averageOfDoubles() {
        assertEquals(6.0, service.average(List.of(2.0, 4.0, 8.0, 10.0)), 0.000001);
    }

    @Test
    public void averageOfEmptyListIsZero() {
        assertEquals(0.0, service.average(List.of()), 0.000001);
    }

    @Test
    public void splitMultiplesOfSevenIntoTwoFiles() throws Exception {
        Path source = folder.newFile("f.txt").toPath();
        Path multiples = folder.newFile("g.txt").toPath();
        Path rest = folder.newFile("h.txt").toPath();
        service.writeInts(source, List.of(14, -7, 0, 5, -3));

        service.splitMultiples(source, multiples, rest, 7);

        assertEquals(List.of(14, -7, 0), service.readInts(multiples));
        assertEquals(List.of(5, -3), service.readInts(rest));
    }

    @Test
    public void splitMultiplesRejectsZeroDivisor() throws Exception {
        Path source = folder.newFile("source.txt").toPath();
        Path multiples = folder.newFile("multiples.txt").toPath();
        Path rest = folder.newFile("rest.txt").toPath();
        service.writeInts(source, List.of(1, 2, 3));

        assertThrows(IllegalArgumentException.class,
                () -> service.splitMultiples(source, multiples, rest, 0));
    }

    @Test
    public void countNonZeroCountsOnlyNonZeroValues() throws Exception {
        Path file = folder.newFile("nonZero.txt").toPath();
        service.writeInts(file, List.of(0, 5, -3, 0, 12));

        assertEquals(3, service.countNonZero(service.readInts(file)));
        assertEquals(0, service.countNonZero(List.of(0, 0)));
    }

    @Test
    public void randomFileContainsRequestedNumberOfValuesInRange() throws Exception {
        Path file = folder.newFile("random.txt").toPath();

        service.createRandomIntFile(file, 20, -100, 100);

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        assertEquals(20, lines.size());
        List<Integer> values = service.readInts(file);
        assertEquals(20, values.size());
        for (int value : values) {
            assertTrue(value >= -100 && value <= 100);
        }
    }

    @Test
    public void writeAndReadDoublesPreserveValues() throws Exception {
        Path file = folder.newFile("doubles.txt").toPath();
        List<Double> values = List.of(0.5, 1.5, -2.25, 10.0);

        service.writeDoubles(file, values);

        assertEquals(values, service.readDoubles(file));
    }

    @Test
    public void cyrillicTextIsReadAsUtf8() throws Exception {
        Path file = folder.newFile("utf8.txt").toPath();
        List<String> lines = List.of("Колледж бизнеса и права", "Город: Минск");
        Files.write(file, lines, StandardCharsets.UTF_8);

        assertEquals(lines, Files.readAllLines(file, StandardCharsets.UTF_8));
        assertTrue(Files.readString(file, StandardCharsets.UTF_8)
                .contains("Колледж бизнеса и права"));
    }
}
