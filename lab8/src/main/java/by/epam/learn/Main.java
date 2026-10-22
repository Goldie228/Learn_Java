package by.epam.learn;

import java.util.Scanner;

/**
 * Лабораторная работа №8 — Вариант 9.
 * Наследование: класс Computer и его наследники GamingPC / OfficePC,
 * а также пример из теории Point1D → Point2D → Point3D.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Компьютеры: полиморфный вывод (Задание 9)");
            System.out.println("2. Пример: цепочка Point1D → Point2D → Point3D");
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

    /** Задание 9 — массив Computer[] из 5 элементов: 3 GamingPC и 2 OfficePC. */
    static void task1() {
        System.out.println("\n--- Задание 9: Компьютеры ---");

        Computer[] computers = new Computer[] {
            new GamingPC("Intel Core i9", 32, 2000, "NVIDIA RTX 4080"),
            new GamingPC("AMD Ryzen 7", 16, 1000, "NVIDIA RTX 3070"),
            new GamingPC("Intel Core i7", 16, 512, "AMD Radeon RX 6700"),
            new OfficePC("Intel Core i5", 8, 256, 21.5),
            new OfficePC("AMD Ryzen 5", 16, 512, 27.0)
        };

        System.out.println("Полиморфный вывод через ссылку базового типа Computer:");
        for (int i = 0; i < computers.length; i++) {
            System.out.printf("%d) %s%n", i + 1, computers[i].displayInfo());
            System.out.println("   Производительность: " + computers[i].getPerformance());
        }

        System.out.println("\nУникальные возможности (instanceof + приведение типа):");
        for (Computer computer : computers) {
            if (computer instanceof GamingPC gaming) {
                System.out.println("   GamingPC: " + gaming.playGame());
            } else if (computer instanceof OfficePC office) {
                System.out.println("   OfficePC: " + office.printDocument());
            }
        }
    }

    /** Пример из теории: конструкторы через super() и this(). */
    static void task2() {
        System.out.println("\n--- Пример: Point1D, Point2D, Point3D ---");

        Point1D p1 = new Point1D(1);
        Point2D p2 = new Point2D(2, 3);
        Point3D p3 = new Point3D(4, 5, 6);
        Point3D defaultPoint = new Point3D();

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println("Конструктор по умолчанию: " + defaultPoint);

        System.out.println("p3 instanceof Point2D: " + (p3 instanceof Point2D));
        System.out.println("p3 instanceof Point1D: " + (p3 instanceof Point1D));
        System.out.println("p2 instanceof Point3D: " + (p2 instanceof Point3D));
        System.out.println("Координаты p3: x=" + p3.getX()
                + ", y=" + p3.getY() + ", z=" + p3.getZ());
    }
}
