package by.epam.learn;

import java.util.Scanner;

/**
 * Лабораторная работа №6 — Вариант 3.
 * Инкапсуляция и модификаторы доступа: класс Ticket + примеры из теории.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Билет: скидка (Задание 1.3)");
            System.out.println("2. Пример: инкапсуляция (MyClass)");
            System.out.println("3. Пример: класс DayWeek");
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

    /** Задание 1.3 — класс Ticket: применить скидку к билету. */
    static void task1() {
        System.out.println("\n--- Задание 1.3: Билет ---");

        Ticket ticket = new Ticket("Рок-концерт", 42, 120.00);
        Ticket ticket1 = new Ticket("Рок-концерт", 42, 120.00, 20);

        System.out.println("До скидки:            " + ticket);

        ticket.applyDiscount();          // скидка по умолчанию 10 %
        System.out.println("После скидки 10%:     " + ticket);

        ticket.applyDiscount(25);        // ещё 25 %
        System.out.println("После скидки ещё 25%: " + ticket);

        System.out.println();
        System.out.println("Проверка инкапсуляции: цена доступна только на чтение —");
        System.out.println("  ticket.getPrice() = " + ticket.getPrice());
        System.out.println("  ticket.price      — ошибка компиляции (поле private)");
    }

    /** Пример из теории: MyClass — private-поле + геттер/сеттер. */
    static void task2() {
        System.out.println("\n--- Пример: инкапсуляция (MyClass) ---");

        MyClass obj = new MyClass();
        System.out.println("Начальное значение: " + obj.getS());
        obj.setS("It's modified!");
        System.out.println("После setS():       " + obj.getS());
    }

    /** Пример из теории: DayWeek — скрытое поле value с проверкой 1..7. */
    static void task3() {
        System.out.println("\n--- Пример: класс DayWeek ---");

        DayWeek day = new DayWeek();
        System.out.println("По умолчанию: " + day.Get() + " (" + day.GetName() + ")");

        day.Set(5);
        System.out.println("После Set(5): " + day.Get() + " (" + day.GetName() + ")");

        day.Set(100); // некорректное значение — игнорируется
        System.out.println("После Set(100): " + day.Get() + " (" + day.GetName() + ") — значение не изменилось");
    }
}
