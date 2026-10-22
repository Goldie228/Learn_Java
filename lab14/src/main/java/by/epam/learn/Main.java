package by.epam.learn;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Лабораторная работа №14 — Вариант 9.
 * Файловый ввод и вывод: пять пунктов задания.
 */
public class Main {

    private static final Path DATA_DIR = Paths.get("data");
    private static final int INT_COUNT = 10;
    private static final int DOUBLE_COUNT = 15;

    private final FileTaskService service = new FileTaskService();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(sc, "Выберите действие: ");

            switch (choice) {
                case 1:
                    task1();
                    break;
                case 2:
                    task2(sc);
                    break;
                case 3:
                    task3();
                    break;
                case 4:
                    task4(sc);
                    break;
                case 5:
                    task5();
                    break;
                case 0:
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========== МЕНЮ ==========");
        System.out.println("1. Файл об учебном заведении: создать и вывести данные");
        System.out.println("2. 10 целых чисел: разность max и min");
        System.out.println("3. 20 случайных чисел: кратные 7 и остальные");
        System.out.println("4. 15 значений силы тока: среднее значение");
        System.out.println("5. 10 целых чисел: количество ненулевых");
        System.out.println("0. Выход");
        System.out.println("==========================");
    }

    /** Пункт 1 — файл об учебном заведении. */
    private void task1() {
        System.out.println("\n--- Пункт 1: файл об учебном заведении ---");
        Path file = DATA_DIR.resolve("task1").resolve("university.txt");
        try {
            service.createUniversityFile(DATA_DIR.resolve("task1"));
            service.printFileInfo(file);
            System.out.println("\nСодержимое файла:");
            service.printContent(file);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }

    /** Пункт 2 — 10 целых чисел и разность максимального и минимального. */
    private void task2(Scanner sc) {
        System.out.println("\n--- Пункт 2: 10 целых чисел ---");
        List<Integer> values;
        if (chooseKeyboard(sc)) {
            values = readIntList(sc, INT_COUNT);
        } else {
            values = List.of(15, -3, 42, 7, -18, 0, 25, -9, 33, 4);
            System.out.println("Демонстрационный набор: " + values);
        }

        Path file = DATA_DIR.resolve("task2").resolve("f.txt");
        try {
            service.writeInts(file, values);
            List<Integer> read = service.readInts(file);
            System.out.println("Числа в файле " + file + ": " + read);
            System.out.println("Разность максимального и минимального: "
                    + service.differenceMaxMin(read));
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }

    /** Пункт 3 — случайные числа, кратные 7 и остальные. */
    private void task3() {
        System.out.println("\n--- Пункт 3: случайные числа и кратные 7 ---");
        Path dir = DATA_DIR.resolve("task3");
        Path source = dir.resolve("f.txt");
        Path multiples = dir.resolve("g.txt");
        Path rest = dir.resolve("h.txt");
        try {
            service.createRandomIntFile(source, 20, -100, 100);
            service.splitMultiples(source, multiples, rest, 7);
            System.out.println("Файл f (20 случайных чисел): " + service.readInts(source));
            System.out.println("Файл g (кратные 7): " + service.readInts(multiples));
            System.out.println("Файл h (остальные): " + service.readInts(rest));
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }

    /** Пункт 4 — 15 значений силы тока и среднее значение. */
    private void task4(Scanner sc) {
        System.out.println("\n--- Пункт 4: сила тока ---");
        List<Double> values;
        if (chooseKeyboard(sc)) {
            values = readDoubleList(sc, DOUBLE_COUNT);
        } else {
            values = List.of(0.5, 1.2, 0.8, 2.4, 1.1, 0.9, 1.7, 2.0,
                    0.6, 1.5, 1.3, 0.7, 1.9, 2.2, 1.0);
            System.out.println("Демонстрационный набор: " + values);
        }

        Path file = DATA_DIR.resolve("task4").resolve("f.txt");
        try {
            service.writeDoubles(file, values);
            List<Double> read = service.readDoubles(file);
            System.out.println("Значения силы тока: " + read);
            System.out.printf("Средняя сила тока: %s А%n",
                    String.format(Locale.ROOT, "%.2f", service.average(read)));
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }

    /** Пункт 5 — 10 целых чисел и количество чисел, не равных нулю. */
    private void task5() {
        System.out.println("\n--- Пункт 5: количество ненулевых чисел ---");
        List<Integer> values = List.of(0, 5, -3, 0, 12, -7, 0, 9, 4, 0);
        System.out.println("Демонстрационный набор: " + values);

        Path file = DATA_DIR.resolve("task5").resolve("f.txt");
        try {
            service.writeInts(file, values);
            List<Integer> read = service.readInts(file);
            System.out.println("Числа в файле " + file + ": " + read);
            System.out.println("Количество чисел, не равных нулю: "
                    + service.countNonZero(read));
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }

    /** Подменю выбора способа ввода. */
    private static boolean chooseKeyboard(Scanner sc) {
        System.out.println("Способ ввода: 1 — с клавиатуры, 2 — демонстрационный набор");
        return readInt(sc, "Выберите способ: ") == 1;
    }

    private static List<Integer> readIntList(Scanner sc, int count) {
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            values.add(readInt(sc, "Введите целое число " + i + " из " + count + ": "));
        }
        return values;
    }

    private static List<Double> readDoubleList(Scanner sc, int count) {
        List<Double> values = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            values.add(readDouble(sc, "Введите значение " + i + " из " + count + ": "));
        }
        return values;
    }

    /** Читает целое число, повторяя запрос при ошибке. */
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNext()) {
                return 0;
            }
            String token = sc.next();
            try {
                return Integer.parseInt(token);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    /** Читает вещественное число, повторяя запрос при ошибке. */
    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNext()) {
                return 0.0;
            }
            String token = sc.next();
            try {
                return Double.parseDouble(token);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }
}
