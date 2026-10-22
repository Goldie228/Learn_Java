package by.epam.learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Файловые операции лабораторной работы №14: текстовые файлы в кодировке
 * UTF-8, чтение и запись чисел, разбиение последовательности по признаку.
 * Все методы создают родительские каталоги и используют try-with-resources.
 */
public class FileTaskService {

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    /** Пункт 1: создаёт файл с общими сведениями об учебном заведении. */
    public Path createUniversityFile(Path dir) throws IOException {
        Files.createDirectories(dir);
        Path file = dir.resolve("university.txt");
        List<String> lines = List.of(
                "Учебное заведение: Колледж бизнеса и права",
                "Город: Минск",
                "Направления подготовки:",
                "- Разработка и сопровождение программного обеспечения",
                "- Экономика",
                "- Право",
                "Форма обучения: дневная и заочная",
                "Количество отделений: 4");

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        return file;
    }

    /** Пункт 1: выводит характеристики файла или каталога. */
    public void printFileInfo(Path path) throws IOException {
        String modified = Files.exists(path)
                ? TIME_FORMAT.format(Files.getLastModifiedTime(path)
                        .toInstant().atZone(ZoneId.systemDefault()))
                : "—";

        System.out.println("Имя файла: " + path.getFileName());
        System.out.println("Размер: " + (Files.exists(path) ? Files.size(path) : 0) + " байт");
        System.out.println("Последняя модификация: " + modified);
        System.out.println("Родительский каталог: "
                + (path.getParent() == null ? "—" : path.getParent()));
        System.out.println("Путь: " + path);
        System.out.println("Абсолютный путь: " + path.toAbsolutePath());
        System.out.println("Существует: " + yesNo(Files.exists(path)));
        System.out.println("Доступно для чтения: " + yesNo(Files.isReadable(path)));
        System.out.println("Доступно для записи: " + yesNo(Files.isWritable(path)));
        System.out.println("Это каталог: " + yesNo(Files.isDirectory(path)));
        System.out.println("Это файл: " + yesNo(Files.isRegularFile(path)));
    }

    private static String yesNo(boolean value) {
        return value ? "да" : "нет";
    }

    /** Пункт 2: записывает целые числа по одному в строке. */
    public void writeInts(Path file, List<Integer> values) throws IOException {
        createParentDirectories(file);
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (Integer value : values) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        }
    }

    /** Пункт 2: читает целые числа, пропуская пустые строки. */
    public List<Integer> readInts(Path file) throws IOException {
        List<Integer> values = new ArrayList<>();
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            if (!line.isBlank()) {
                values.add(Integer.parseInt(line.trim()));
            }
        }
        return values;
    }

    /** Пункт 2: разность максимального и минимального значений. */
    public int differenceMaxMin(List<Integer> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Список чисел пуст");
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        return max - min;
    }

    /** Пункт 4: записывает вещественные числа по одному в строке. */
    public void writeDoubles(Path file, List<Double> values) throws IOException {
        createParentDirectories(file);
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (Double value : values) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        }
    }

    /** Пункт 4: читает вещественные числа. */
    public List<Double> readDoubles(Path file) throws IOException {
        List<Double> values = new ArrayList<>();
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            if (!line.isBlank()) {
                values.add(Double.parseDouble(line.trim()));
            }
        }
        return values;
    }

    /** Пункт 4: среднее значение; для пустого списка возвращает 0.0. */
    public double average(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    /** Пункт 3: создаёт файл из случайных целых чисел в диапазоне [min, max]. */
    public void createRandomIntFile(Path file, int count, int min, int max) throws IOException {
        if (count < 0) {
            throw new IllegalArgumentException("Количество чисел не может быть отрицательным");
        }
        if (min > max) {
            throw new IllegalArgumentException("Нижняя граница больше верхней");
        }
        createParentDirectories(file);
        Random random = new Random();
        int bound = max - min + 1;
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (int i = 0; i < count; i++) {
                writer.write(String.valueOf(random.nextInt(bound) + min));
                writer.newLine();
            }
        }
    }

    /** Пункт 3: числа, кратные divisor, — в один файл, остальные — в другой. */
    public void splitMultiples(Path source, Path multiples, Path rest, int divisor)
            throws IOException {
        if (divisor == 0) {
            throw new IllegalArgumentException("Делитель не может быть нулём");
        }
        List<Integer> multipleValues = new ArrayList<>();
        List<Integer> restValues = new ArrayList<>();
        for (int value : readInts(source)) {
            if (value % divisor == 0) {
                multipleValues.add(value);
            } else {
                restValues.add(value);
            }
        }
        writeInts(multiples, multipleValues);
        writeInts(rest, restValues);
    }

    /** Пункт 5: количество чисел, не равных нулю. */
    public int countNonZero(List<Integer> values) {
        if (values == null) {
            return 0;
        }
        int count = 0;
        for (int value : values) {
            if (value != 0) {
                count++;
            }
        }
        return count;
    }

    /** Выводит содержимое текстового файла построчно. */
    public void printContent(Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private static void createParentDirectories(Path file) throws IOException {
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
    }
}
