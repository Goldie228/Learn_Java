package by.epam.learn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Лабораторная работа №10 — Вариант 9.
 * Интерфейсы: Readable, Lendable; классы Novel, Textbook и пример Shape.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Книги: чтение (Readable)");
            System.out.println("2. Книги: выдача и возврат (Lendable)");
            System.out.println("3. Пример: фигура (Drawable, Moveable, Resizable)");
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

    /** Создаёт 3 романа и 2 учебника. */
    static List<Readable> createBooks() {
        List<Readable> books = new ArrayList<>();
        books.add(new Novel("Мастер и Маргарита", "М. А. Булгаков", 480));
        books.add(new Novel("Преступление и наказание", "Ф. М. Достоевский", 672));
        books.add(new Novel("1984", "Джордж Оруэлл", 320));
        books.add(new Textbook("Информатика", "И. Н. Блинов", 560, "информатика"));
        books.add(new Textbook("Алгебра", "А. Г. Мерзляк", 288, "математика"));
        return books;
    }

    /** Возвращает название книги через ссылку на интерфейс. */
    static String titleOf(Readable book) {
        if (book instanceof Novel novel) {
            return novel.getTitle();
        }
        if (book instanceof Textbook textbook) {
            return textbook.getTitle();
        }
        return "неизвестная книга";
    }

    /** Пункт 1 — полиморфизм через интерфейс Readable. */
    static void task1() {
        System.out.println("\n--- Полиморфизм через интерфейс Readable ---");

        List<Readable> books = createBooks();
        for (Readable book : books) {
            System.out.println(book.read());
            System.out.println("  Автор: " + authorOf(book));
            System.out.println("  Объём: " + Readable.formatPages(book.getPages()));
            System.out.println("  Время чтения: " + book.getReadingTimeMinutes() + " мин");
        }

        int totalPages = 0;
        int totalMinutes = 0;
        for (Readable book : books) {
            totalPages += book.getPages();
            totalMinutes += book.getReadingTimeMinutes();
        }
        System.out.println("Всего книг: " + books.size());
        System.out.println("Суммарный объём: " + Readable.formatPages(totalPages));
        System.out.println("Суммарное время чтения: " + totalMinutes + " мин");
    }

    /** Автор книги через ссылку на интерфейс Readable. */
    static String authorOf(Readable book) {
        if (book instanceof Novel novel) {
            return novel.getAuthor();
        }
        if (book instanceof Textbook textbook) {
            return textbook.getAuthor();
        }
        return "неизвестен";
    }

    /** Пункт 2 — выдача и возврат книг через интерфейс Lendable. */
    static void task2() {
        System.out.println("\n--- Полиморфизм через интерфейс Lendable ---");

        List<Readable> books = createBooks();
        List<Lendable> lendables = new ArrayList<>();
        for (Readable book : books) {
            if (book instanceof Lendable lendable) {
                lendables.add(lendable);
            }
        }

        System.out.println("Выдаём все книги:");
        for (int i = 0; i < lendables.size(); i++) {
            boolean issued = lendables.get(i).lend();
            System.out.println("  " + (i + 1) + ". " + titleOf(books.get(i))
                    + " → lend(): " + issued
                    + ", isAvailable(): " + lendables.get(i).isAvailable());
        }

        Lendable first = lendables.get(0);
        System.out.println("Повторная выдача книги «" + titleOf(books.get(0)) + "»:");
        System.out.println("  lend(): " + first.lend()
                + ", isAvailable(): " + first.isAvailable());

        System.out.println("Возврат книги «" + titleOf(books.get(0)) + "»:");
        System.out.println("  returnBook(): " + first.returnBook()
                + ", isAvailable(): " + first.isAvailable());

        System.out.println("Повторный возврат книги «" + titleOf(books.get(0)) + "»:");
        System.out.println("  returnBook(): " + first.returnBook()
                + ", isAvailable(): " + first.isAvailable());
    }

    /** Пункт 3 — пример из теории: класс Shape и три интерфейса. */
    static void task3() {
        System.out.println("\n--- Пример: Shape реализует три интерфейса ---");

        Shape shape = new Shape(1, 2, 10.0);
        shape.draw();
        shape.move(3, 4);
        shape.resize(1.5);
        shape.draw();

        Drawable drawable = shape;
        Moveable moveable = shape;
        Resizable resizable = shape;
        System.out.println("Ссылки на интерфейсы: "
                + (drawable instanceof Shape)
                + ", " + (moveable instanceof Shape)
                + ", " + (resizable instanceof Shape));
    }
}
