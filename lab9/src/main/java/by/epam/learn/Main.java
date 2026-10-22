package by.epam.learn;

import java.util.Scanner;

/**
 * Лабораторная работа №9 — Вариант 9.
 * Абстрактные классы: Transport и его наследники Truck / Ship,
 * а также пример из теории Animal / Dog.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Транспорт: полиморфный вывод (Задание 9)");
            System.out.println("2. Пример: Animal и Dog (порядок конструкторов)");
            System.out.println("3. Почему нельзя создать объект Transport");
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
                    task2();
                    break;
                case 3:
                    task3();
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

    /** Задание 9 — Transport[] из 5 элементов: 3 Truck и 2 Ship. */
    static void task1() {
        System.out.println("\n--- Задание 9: Транспорт ---");

        Transport[] transports = new Transport[] {
            new Truck(20.0, 80.0, 300.0, 15.0),
            new Truck(10.0, 60.0, 150.0, 8.0),
            new Truck(25.0, 90.0, 400.0, 20.0),
            new Ship(500.0, 80.0, 1000.0, 300.0),
            new Ship(750.0, 40.0, 1500.0, 450.0)
        };

        double distance = 500.0;
        System.out.println("Полиморфный вывод через ссылку базового типа Transport:");
        for (int i = 0; i < transports.length; i++) {
            Transport transport = transports[i];
            System.out.printf("%d) %s%n", i + 1, transport.displayInfo());
            System.out.println("   Доставка: " + transport.deliver());
            System.out.printf("   Время доставки на %.0f км: %.2f ч%n",
                    distance, transport.calculateTime(distance));
        }

        System.out.println("\nУникальные поля наследников (instanceof + приведение типа):");
        for (Transport transport : transports) {
            if (transport instanceof Truck truck) {
                System.out.printf("   Truck, вес груза: %.1f т%n",
                        truck.getCargoWeight());
            } else if (transport instanceof Ship ship) {
                System.out.printf("   Ship, тоннаж: %.1f т%n",
                        ship.getTonnage());
            }
        }
    }

    /** Пример из теории: порядок вызова конструкторов Animal и Dog. */
    static void task2() {
        System.out.println("\n--- Пример: Animal и Dog ---");

        Dog dog = new Dog("Шарик", 3, "Овчарка");
        System.out.println("Кличка: " + dog.getName());
        System.out.println("Возраст: " + dog.getAge());
        System.out.println("Порода: " + dog.getBreed());
        System.out.println("Звук: " + dog.makeSound());
        System.out.println("dog instanceof Animal: " + (dog instanceof Animal));
    }

    /** Пояснение: абстрактный класс нельзя инстанцировать. */
    static void task3() {
        System.out.println("\n--- Почему нельзя создать объект Transport ---");
        System.out.println("Transport — абстрактный класс, он содержит абстрактные методы");
        System.out.println("deliver() и calculateTime(double) без реализации.");
        System.out.println("Поэтому строка");
        System.out.println("    Transport t = new Transport(10, 60, 100);");
        System.out.println("не компилируется: \"Transport is abstract; cannot be instantiated\".");
        System.out.println("Но ссылка на абстрактный тип допустима:");
        System.out.println("    Transport t = new Truck(20, 80, 300, 15);");

        // Ошибка компиляции: Transport is abstract; cannot be instantiated
        // Transport broken = new Transport(10, 60, 100);

        Transport transport = new Truck(20.0, 80.0, 300.0, 15.0);
        System.out.println("Работает через наследника: " + transport.deliver());
    }
}
