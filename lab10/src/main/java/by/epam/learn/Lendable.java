package by.epam.learn;

/**
 * Интерфейс выдачи книги: позволяет выдать книгу, вернуть её
 * и узнать текущую доступность.
 */
public interface Lendable {

    /** Выдать книгу. Возвращает {@code true}, если выдача удалась. */
    boolean lend();

    /** Вернуть книгу. Возвращает {@code true}, если возврат удался. */
    boolean returnBook();

    /** Доступна ли книга для выдачи. */
    boolean isAvailable();
}
