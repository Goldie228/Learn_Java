package by.epam.learn;

/**
 * Проверяемое исключение: неверный пароль при аутентификации.
 * Хранит имя пользователя и номер неудачной попытки.
 */
public class AuthenticationException extends Exception {

    private final String username;
    private final int attempts;

    public AuthenticationException(String username, int attempts) {
        super("Пользователь " + username + ": неверный пароль (попытка " + attempts + ")");
        this.username = username;
        this.attempts = attempts;
    }

    public String getUsername() {
        return username;
    }

    public int getAttempts() {
        return attempts;
    }
}
