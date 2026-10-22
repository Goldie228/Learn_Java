package by.epam.learn;

/**
 * Проверяемое исключение: переданные данные пользователя некорректны.
 * Поле {@code field} указывает, какое именно поле не прошло проверку.
 */
public class InvalidUserDataException extends Exception {

    private final String field;

    public InvalidUserDataException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
