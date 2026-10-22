package by.epam.learn;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Лабораторная работа №12 — Вариант 9.
 * Обобщённые типы и коллекции: инструменты (Map) и отели (List, сортировка).
 */
public class Main {

    private static final HotelService HOTEL_SERVICE = new HotelService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Задание 1: Map<String, Tool> (8 инструментов)");
            System.out.println("2. Задание 2.1: отели со средним рейтингом < 3");
            System.out.println("3. Задание 2.2: повышение звёзд отелям с рейтингом >= 4");
            System.out.println("4. Задание 2.3: отели с заданным количеством звёзд");
            System.out.println("5. Задание 2.4: сортировка по среднему рейтингу");
            System.out.println("6. Пример из теории: Box<T> и Pair<K, V>");
            System.out.println("0. Выход");
            System.out.println("==========================");
            System.out.print("Выберите действие: ");

            String choiceStr = sc.next();
            sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    task1();
                    break;
                case 2:
                    task2LowRated();
                    break;
                case 3:
                    task3RaiseStars();
                    break;
                case 4:
                    task4ByStars();
                    break;
                case 5:
                    task5SortedByRating();
                    break;
                case 6:
                    task6Generics();
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

    /** Набор из 8 инструментов: 3 молотка, 3 отвёртки, 2 ключа. */
    static Map<String, Tool> createToolsMap() {
        Map<String, Tool> tools = new LinkedHashMap<>();
        tools.put("Молоток слесарный", new Hammer("Молоток слесарный", 101, 1.20, 30.0));
        tools.put("Молоток отделочный", new Hammer("Молоток отделочный", 102, 0.85, 28.0));
        tools.put("Молоток-гвоздодёр", new Hammer("Молоток-гвоздодёр", 103, 1.45, 32.0));
        tools.put("Отвёртка крестовая", new Screwdriver("Отвёртка крестовая", 201, 0.15, "PH2"));
        tools.put("Отвёртка плоская", new Screwdriver("Отвёртка плоская", 202, 0.12, "SL3"));
        tools.put("Отвёртка прецизионная", new Screwdriver("Отвёртка прецизионная", 203, 0.05, "PH0"));
        tools.put("Ключ рожковый", new Wrench("Ключ рожковый", 301, 0.35, 17));
        tools.put("Ключ комбинированный", new Wrench("Ключ комбинированный", 302, 0.42, 19));
        return tools;
    }

    /** Набор отелей, покрывающий все ветки операций. */
    static List<Hotel> createHotels() {
        return new java.util.ArrayList<>(List.of(
                new Hotel(1, "Гранд Отель", "Минск", 5, List.of(5, 5, 4)),
                new Hotel(2, "Сити Стар", "Минск", 4, List.of(5, 4, 4)),
                new Hotel(3, "Астория", "Гомель", 4, List.of(5, 4)),
                new Hotel(4, "Бригантина", "Брест", 3, List.of(5, 4)),
                new Hotel(5, "Турист", "Витебск", 3, List.of(3, 3, 3)),
                new Hotel(6, "Припять", "Мозырь", 2, List.of(2, 3)),
                new Hotel(7, "Юбилейный", "Гродно", 3, List.of()),
                new Hotel(8, "Спутник", "Могилёв", 2, List.of(4, 5, 3))));
    }

    /** Задание 1: Map с названием инструмента в качестве ключа. */
    static void task1() {
        System.out.println("\n--- Задание 1: Map<String, Tool> ---");
        Map<String, Tool> tools = createToolsMap();
        System.out.println("Размер Map: " + tools.size());

        System.out.println("\nКлючи (keySet):");
        for (String key : tools.keySet()) {
            System.out.println("  " + key);
        }

        System.out.println("\nПары «ключ — значение» (entrySet):");
        for (Map.Entry<String, Tool> entry : tools.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue().displayInfo());
        }

        System.out.println("\nТо же через forEach:");
        tools.forEach((key, tool) ->
                System.out.println("  " + key + " → " + tool.displayInfo()));

        int hammers = 0;
        int screwdrivers = 0;
        int wrenches = 0;
        for (Tool tool : tools.values()) {
            if (tool instanceof Hammer) {
                hammers++;
            } else if (tool instanceof Screwdriver) {
                screwdrivers++;
            } else if (tool instanceof Wrench) {
                wrenches++;
            }
        }
        System.out.println("\nСтатистика по типам (instanceof):");
        System.out.println("  Hammer:      " + hammers);
        System.out.println("  Screwdriver: " + screwdrivers);
        System.out.println("  Wrench:      " + wrenches);

        System.out.println("\n--- Демонстрация перезаписи значения по ключу ---");
        Map<String, Tool> demo = new LinkedHashMap<>();
        demo.put("Напильник", new Wrench("Напильник", 401, 0.30, 10));
        System.out.println("Размер до повторной вставки: " + demo.size());
        System.out.println("Значение до:   " + demo.get("Напильник").displayInfo());
        demo.put("Напильник", new Screwdriver("Напильник", 402, 0.10, "PH1"));
        System.out.println("Размер после повторной вставки: " + demo.size());
        System.out.println("Значение после: " + demo.get("Напильник").displayInfo());
        System.out.println("Ключ остался один, значение заменено новым объектом.");
    }

    /** Задание 2.1: отели со средним рейтингом меньше 3. */
    static void task2LowRated() {
        System.out.println("\n--- Задание 2.1: отели со средним рейтингом < 3 ---");
        List<Hotel> hotels = createHotels();
        List<Hotel> result = HOTEL_SERVICE.lowRated(hotels);
        System.out.println("Найдено отелей: " + result.size());
        HOTEL_SERVICE.printHotels(result);
    }

    /** Задание 2.2: повышение звёздности лучшим отелям. */
    static void task3RaiseStars() {
        System.out.println("\n--- Задание 2.2: повышение звёзд отелям с рейтингом >= 4 ---");
        List<Hotel> hotels = createHotels();
        System.out.println("До повышения:");
        HOTEL_SERVICE.printHotels(hotels);
        HOTEL_SERVICE.raiseStarsForBest(hotels);
        System.out.println("\nПосле повышения (не выше 5 звёзд):");
        HOTEL_SERVICE.printHotels(hotels);
    }

    /** Задание 2.3: печать отелей с заданным количеством звёзд. */
    static void task4ByStars() {
        System.out.println("\n--- Задание 2.3: отели с 4 звёздами ---");
        HOTEL_SERVICE.printHotels(createHotels(), 4);
    }

    /** Задание 2.4: сортировка по убыванию рейтинга с алфавитной ничьей. */
    static void task5SortedByRating() {
        System.out.println("\n--- Задание 2.4: сортировка по среднему рейтингу ---");
        List<Hotel> sorted = HOTEL_SERVICE.sortedByRating(createHotels());
        System.out.println("ID | Название | Средний рейтинг");
        for (Hotel hotel : sorted) {
            System.out.printf("  %d | %s | %.2f%n",
                    hotel.getId(), hotel.getName(), hotel.getAverageRating());
        }
    }

    /** Пример из теории: обобщённые классы Box и Pair. */
    static void task6Generics() {
        System.out.println("\n--- Пример: Box<T> и Pair<K, V> ---");
        Box<String> stringBox = new Box<>("Hello");
        Box<Integer> intBox = new Box<>(123);
        Box<Double> doubleBox = new Box<>(3.14);

        System.out.println("Box<String>:  " + stringBox.getValue()
                + " (тип: " + stringBox.getValue().getClass().getSimpleName() + ")");
        System.out.println("Box<Integer>: " + intBox.getValue()
                + " (тип: " + intBox.getValue().getClass().getSimpleName() + ")");
        System.out.println("Box<Double>:  " + doubleBox.getValue()
                + " (тип: " + doubleBox.getValue().getClass().getSimpleName() + ")");

        Pair<String, Integer> pair = new Pair<>("Возраст", 25);
        Pair<String, Double> price = new Pair<>("Цена", 19.99);
        System.out.println("Pair<String, Integer>: " + pair.getKey() + " = " + pair.getValue());
        System.out.println("Pair<String, Double>:  " + price.getKey() + " = " + price.getValue());
        System.out.println("Приведение типов не требуется: компилятор знает типы параметров.");
    }
}
