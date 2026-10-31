package by.epam.learn;

import java.util.regex.Pattern;

/**
 * Пользователь системы. Конструктор проверяет исходные данные, а сеттер
 * email — каждое новое значение; ошибки выбрасываются как
 * {@link InvalidUserDataException}.
 */
public class User {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 150;

    private final String username;
    private String email;
    private final String password;
    private final int age;
    private int failedAttempts;

    public User(String username, String email, String password, int age)
            throws InvalidUserDataException {
        validateUsername(username);
        validateEmail(email);
        validatePassword(password);
        validateAge(age);

        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    private static void validateUsername(String username) throws InvalidUserDataException {
        if (username == null || username.isBlank()) {
            throw new InvalidUserDataException("username",
                    "Имя пользователя не может быть пустым");
        }
    }

    private static void validateEmail(String email) throws InvalidUserDataException {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidUserDataException("email",
                    "Некорректный email: " + email);
        }
    }

    private static void validatePassword(String password) throws InvalidUserDataException {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            throw new InvalidUserDataException("password",
                    "Пароль должен содержать не менее " + MIN_PASSWORD_LENGTH + " символов");
        }
    }

    private static void validateAge(int age) throws InvalidUserDataException {
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new InvalidUserDataException("age",
                    "Возраст должен быть в диапазоне от " + MIN_AGE + " до " + MAX_AGE + ": " + age);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    /** Проверяет пароль, не изменяя счётчик неудачных попыток. */
    public boolean checkPassword(String password) {
        return password != null && this.password.equals(password);
    }

    /**
     * Аутентификация пользователя. При неверном пароле увеличивает счётчик
     * неудачных попыток и выбрасывает {@link AuthenticationException}.
     */
    public void authenticate(String password) throws AuthenticationException {
        if (!checkPassword(password)) {
            failedAttempts++;
            throw new AuthenticationException(username, failedAttempts);
        }
        failedAttempts = 0;
    }

    /** Сеттер email: проверяет формат до изменения поля. */
    public void setEmail(String newEmail) throws InvalidUserDataException {
        validateEmail(newEmail);
        this.email = newEmail;
    }

    /** Обновляет email; оставлено как метод из задания. */
    public void updateEmail(String newEmail) throws InvalidUserDataException {
        setEmail(newEmail);
    }
}
