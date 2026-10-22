package by.epam.learn;

/**
 * Проверяемое исключение: пользователь с указанным именем не найден.
 */
public class UserNotFoundException extends Exception {

    private final String username;

    public UserNotFoundException(String username) {
        super("Пользователь " + username + " не найден");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
