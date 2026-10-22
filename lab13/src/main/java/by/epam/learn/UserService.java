package by.epam.learn;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Хранилище пользователей: регистрация, поиск, вход и обновление email.
 * Порядок пользователей сохраняется благодаря {@link LinkedHashMap}.
 */
public class UserService {

    private final Map<String, User> users = new LinkedHashMap<>();

    /** Регистрирует пользователя; повторное имя запрещено. */
    public void register(User user) throws InvalidUserDataException {
        if (user == null) {
            throw new InvalidUserDataException("user", "Пользователь не может быть null");
        }
        String username = user.getUsername();
        if (users.containsKey(username)) {
            throw new InvalidUserDataException("username",
                    "Пользователь с именем " + username + " уже зарегистрирован");
        }
        users.put(username, user);
    }

    /** Находит пользователя по имени. */
    public User find(String username) throws UserNotFoundException {
        User user = username == null ? null : users.get(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return user;
    }

    /** Выполняет вход: сначала поиск пользователя, затем проверка пароля. */
    public void login(String username, String password)
            throws UserNotFoundException, AuthenticationException {
        find(username).authenticate(password);
    }

    /** Обновляет email пользователя. */
    public void updateEmail(String username, String newEmail)
            throws UserNotFoundException, InvalidUserDataException {
        find(username).updateEmail(newEmail);
    }

    public int size() {
        return users.size();
    }

    /** Возвращает копию списка пользователей в порядке регистрации. */
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }
}
