package by.epam.learn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Лабораторная работа №11 — Вариант 9.
 * Полиморфизм: перегрузка и переопределение методов класса Computer.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Перегрузка методов run()");
            System.out.println("2. Полиморфный список компьютеров");
            System.out.println("3. instanceof и нисходящее приведение");
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

    /** Создаёт 3 игровых и 2 офисных компьютера. */
    static List<Computer> createComputers() {
        List<Computer> computers = new ArrayList<>();
        computers.add(new GamingPC("Aurora X", 32, 2000, "NVIDIA RTX 4080"));
        computers.add(new GamingPC("Titan Pro", 16, 1000, "AMD Radeon RX 7800 XT"));
        computers.add(new GamingPC("Vortex Mini", 16, 512, "NVIDIA RTX 4060"));
        computers.add(new OfficePC("Office Line 1", 8, 256, 23.8));
        computers.add(new OfficePC("Office Line 2", 16, 512, 27.0));
        return computers;
    }

    /** Пункт 1 — статический полиморфизм: три версии run(). */
    static void task1() {
        System.out.println("\n--- Статический полиморфизм (перегрузка) ---");

        Computer computer = new Computer("Base PC", 8, 256);
        System.out.println("computer.run():       " + computer.run());
        System.out.println("computer.run(String): " + computer.run("Word"));
        System.out.println("computer.run(int):    " + computer.run(12));

        GamingPC gaming = new GamingPC("Aurora X", 32, 2000, "NVIDIA RTX 4080");
        System.out.println("\nНаследник использует унаследованные перегрузки:");
        System.out.println("gaming.run():         " + gaming.run());
        System.out.println("gaming.run(String):   " + gaming.run("Браузер"));
        System.out.println("gaming.run(int):      " + gaming.run(24));
        System.out.println("gaming.run(String,int): " + gaming.run("Cyberpunk 2077", 120));
    }

    /** Пункт 2 — динамический полиморфизм через ссылку на Computer. */
    static void task2() {
        System.out.println("\n--- Статический полиморфизм (перегрузка) ---");
        Computer base = new Computer("Base PC", 8, 256);
        System.out.println("run():          " + base.run());
        System.out.println("run(String):    " + base.run("Word"));
        System.out.println("run(int):       " + base.run(12));

        System.out.println("\n--- Динамический полиморфизм (переопределение) ---");
        List<Computer> computers = createComputers();
        for (Computer computer : computers) {
            System.out.println(computer.displayInfo());
            System.out.println("  getPerformance() через Computer: " + computer.getPerformance());
        }
    }

    /** Пункт 3 — instanceof, нисходящее приведение и собственные перегрузки. */
    static void task3() {
        System.out.println("\n--- instanceof и нисходящее приведение ---");

        List<Computer> computers = createComputers();
        for (Computer computer : computers) {
            if (computer instanceof GamingPC gaming) {
                System.out.println(computer.getModel() + " — это GamingPC");
                System.out.println("  " + gaming.run("Cyberpunk 2077", 120));
            } else if (computer instanceof OfficePC office) {
                System.out.println(computer.getModel() + " — это OfficePC");
                System.out.println("  " + office.run("Excel", true));
                System.out.println("  " + office.run("Excel", false));
            } else {
                System.out.println(computer.getModel() + " — базовый Computer");
            }
        }
    }
}
