package by.epam.learn;

import java.util.Scanner;

/**
 * Лабораторная работа №5 — Вариант 3.
 * Классы и объекты: Rectangle, Book, Gender/Person + пример Cat.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Прямоугольник: площадь и периметр (Задание 1.3)");
            System.out.println("2. Книги: самая старая и самая новая (Задание 2.3)");
            System.out.println("3. Пол: мужчина и женщина (Задание 3.3)");
            System.out.println("4. Пример из теории: класс Cat");
            System.out.println("0. Выход");
            System.out.println("==========================");
            System.out.print("Выберите действие: ");

            String choiceStr = sc.next();
            sc.nextLine(); // дочитать перевод строки
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
                case 4:
                    task4();
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

    /** Задание 1.3 — класс Rectangle: площадь и периметр нескольких прямоугольников. */
    static void task1() {
        System.out.println("\n--- Задание 1.3: Прямоугольник ---");

        Rectangle[] rectangles = {
            new Rectangle(3, 4),
            new Rectangle(5.5, 2),
            new Rectangle(10, 10)
        };

        System.out.println("Параметры прямоугольников:");
        for (Rectangle r : rectangles) {
            System.out.println("  " + r);
        }
    }

    /** Задание 2.3 — массив из 5 книг: самая старая и самая новая. */
    static void task2() {
        System.out.println("\n--- Задание 2.3: Книги ---");

        Book[] books = {
            new Book("Война и мир", "Л. Н. Толстой", 1869),
            new Book("Преступление и наказание", "Ф. М. Достоевский", 1866),
            new Book("Мастер и Маргарита", "М. А. Булгаков", 1967),
            new Book("Евгений Онегин", "А. С. Пушкин", 1833),
            new Book("Тихий Дон", "М. А. Шолохов", 1940)
        };

        System.out.println("Список книг:");
        System.out.printf("  %-30s | %-20s | %s%n", "НАЗВАНИЕ", "АВТОР", "ГОД");
        System.out.println("  " + "-".repeat(62));
        for (Book b : books) {
            System.out.println("  " + b);
        }

        Book oldest = books[0];
        Book newest = books[0];
        for (Book b : books) {
            if (b.getYear() < oldest.getYear()) {
                oldest = b;
            }
            if (b.getYear() > newest.getYear()) {
                newest = b;
            }
        }

        System.out.println();
        System.out.println("Самая старая книга: " + oldest.getTitle() + " (" + oldest.getYear() + ")");
        System.out.println("Самая новая книга:  " + newest.getTitle() + " (" + newest.getYear() + ")");
        System.out.println(oldest);
    }

    /** Задание 3.3 — перечисление Gender и класс Person. */
    static void task3() {
        System.out.println("\n--- Задание 3.3: Пол ---");

        Person man = new Person("Иван", Gender.MALE);
        Person woman = new Person("Мария", Gender.FEMALE);

        System.out.println("Человек 1: " + man);
        System.out.println("Человек 2: " + woman);

        System.out.println();
        System.out.println("Все значения перечисления Gender:");
        for (Gender g : Gender.values()) {
            System.out.println("  " + g.name() + " (ordinal=" + g.ordinal() + ", " + g.getRu() + ")");
        }
    }

    /** Пример из теоретических сведений: класс Cat. */
    static void task4() {
        System.out.println("\n--- Пример из теории: класс Cat ---");

        Cat ourcat = new Cat("Барсик", 5, "рыжий");
        System.out.println("Кот: " + ourcat);
        ourcat.eat();
        ourcat.sleep();
        String say = ourcat.speak("Play with me");
        System.out.println(say);
    }
}
