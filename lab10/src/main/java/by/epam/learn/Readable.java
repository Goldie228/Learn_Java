package by.epam.learn;

/**
 * Интерфейс читаемой сущности: книга умеет сообщать о процессе чтения
 * и возвращать количество страниц.
 */
public interface Readable {

    /** Сообщение о чтении книги. */
    String read();

    /** Количество страниц. */
    int getPages();

    /**
     * Время чтения в минутах. Метод по умолчанию: 2 минуты на страницу.
     */
    default int getReadingTimeMinutes() {
        return getPages() * 2;
    }

    /** Статический метод форматирования количества страниц. */
    static String formatPages(int pages) {
        return pages + " стр.";
    }
}
