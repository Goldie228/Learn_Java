package by.epam.learn;

/**
 * Класс "Книга" (Book).
 *
 * Задание 2, вариант 3: поля title, author, year (int).
 * Используется для массива из 5 книг и поиска самой старой и самой новой.
 */
public class Book {

    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("%-30s | %-20s | %d", title, author, year);
    }
}
