package by.epam.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Модульные тесты системы пользователей (лабораторная работа №13).
 * Проверяются как успешные операции, так и отрицательные сценарии.
 */
public class Lab13Test {

    private UserService service;

    @Before
    public void setUp() throws InvalidUserDataException {
        service = new UserService();
        service.register(new User("alex", "alex@mail.ru", "secret1", 20));
        service.register(new User("maria", "maria@mail.by", "qwerty12", 22));
    }

    // ---------- Проверка данных конструктором User ----------

    @Test(expected = InvalidUserDataException.class)
    public void constructorRejectsEmptyUsername() throws InvalidUserDataException {
        new User("", "user@mail.ru", "secret1", 20);
    }

    @Test
    public void constructorRejectsBlankUsernameAndReportsField() {
        try {
            new User("   ", "user@mail.ru", "secret1", 20);
            fail("Ожидалось InvalidUserDataException");
        } catch (InvalidUserDataException e) {
            assertEquals("username", e.getField());
        }
    }

    @Test
    public void constructorRejectsInvalidEmail() {
        InvalidUserDataException e = assertThrows(InvalidUserDataException.class,
                () -> new User("user", "user.mail.ru", "secret1", 20));
        assertEquals("email", e.getField());
    }

    @Test
    public void constructorRejectsShortPassword() {
        InvalidUserDataException e = assertThrows(InvalidUserDataException.class,
                () -> new User("user", "user@mail.ru", "12345", 20));
        assertEquals("password", e.getField());
    }

    @Test
    public void constructorRejectsNegativeAge() {
        InvalidUserDataException e = assertThrows(InvalidUserDataException.class,
                () -> new User("user", "user@mail.ru", "secret1", -1));
        assertEquals("age", e.getField());
    }

    @Test
    public void constructorRejectsTooLargeAge() {
        InvalidUserDataException e = assertThrows(InvalidUserDataException.class,
                () -> new User("user", "user@mail.ru", "secret1", 151));
        assertEquals("age", e.getField());
    }

    @Test
    public void constructorAcceptsValidUserAndReturnsGetters() throws InvalidUserDataException {
        User user = new User("ivan", "ivan.petrov+test@mail.ru", "pass123", 35);

        assertEquals("ivan", user.getUsername());
        assertEquals("ivan.petrov+test@mail.ru", user.getEmail());
        assertEquals(35, user.getAge());
        assertTrue(user.checkPassword("pass123"));
        assertFalse(user.checkPassword("other"));
    }

    // ---------- Аутентификация и обновление email ----------

    @Test
    public void authenticateSucceedsWithCorrectPassword()
            throws InvalidUserDataException, AuthenticationException {
        User user = new User("ivan", "ivan@mail.ru", "secret1", 30);

        user.authenticate("secret1");

        assertTrue(user.checkPassword("secret1"));
    }

    @Test
    public void authenticateThrowsOnWrongPassword() throws InvalidUserDataException {
        User user = new User("ivan", "ivan@mail.ru", "secret1", 30);

        AuthenticationException e = assertThrows(AuthenticationException.class,
                () -> user.authenticate("wrong"));

        assertEquals("ivan", e.getUsername());
        assertEquals(1, e.getAttempts());
        assertTrue(e.getMessage().contains("неверный пароль"));
    }

    @Test
    public void failedAttemptsAreCountedAndResetAfterSuccess()
            throws InvalidUserDataException, AuthenticationException {
        User user = new User("ivan", "ivan@mail.ru", "secret1", 30);

        assertThrows(AuthenticationException.class, () -> user.authenticate("wrong"));
        AuthenticationException second = assertThrows(AuthenticationException.class,
                () -> user.authenticate("wrong"));
        assertEquals(2, second.getAttempts());

        user.authenticate("secret1");
        AuthenticationException afterReset = assertThrows(AuthenticationException.class,
                () -> user.authenticate("wrong"));
        assertEquals(1, afterReset.getAttempts());
    }

    @Test
    public void updateEmailChangesAddress() throws InvalidUserDataException {
        User user = new User("ivan", "ivan@mail.ru", "secret1", 30);

        user.updateEmail("new.address@mail.by");

        assertEquals("new.address@mail.by", user.getEmail());
    }

    @Test
    public void updateEmailRejectsInvalidAddressAndKeepsOldValue() throws InvalidUserDataException {
        User user = new User("ivan", "ivan@mail.ru", "secret1", 30);

        InvalidUserDataException e = assertThrows(InvalidUserDataException.class,
                () -> user.updateEmail("new.address@"));

        assertEquals("email", e.getField());
        assertEquals("ivan@mail.ru", user.getEmail());
    }

    // ---------- UserService ----------

    @Test
    public void registerAndFindUser() throws InvalidUserDataException, UserNotFoundException {
        User registered = new User("olga", "olga@mail.by", "olga2024", 24);
        service.register(registered);

        assertEquals(3, service.size());
        assertEquals(registered, service.find("olga"));
        assertEquals("olga@mail.by", service.find("olga").getEmail());
    }

    @Test
    public void registerRejectsDuplicateUsername() {
        InvalidUserDataException e = assertThrows(InvalidUserDataException.class,
                () -> service.register(new User("alex", "other@mail.ru", "secret1", 30)));

        assertEquals("username", e.getField());
        assertTrue(e.getMessage().contains("уже зарегистрирован"));
        assertEquals(2, service.size());
    }

    @Test
    public void findUnknownUserThrowsWithUsername() {
        UserNotFoundException e = assertThrows(UserNotFoundException.class,
                () -> service.find("ghost"));

        assertEquals("ghost", e.getUsername());
        assertEquals("Пользователь ghost не найден", e.getMessage());
    }

    @Test
    public void loginSucceedsForRegisteredUser()
            throws UserNotFoundException, AuthenticationException {
        service.login("alex", "secret1");

        assertEquals("alex@mail.ru", service.find("alex").getEmail());
    }

    @Test
    public void loginWithWrongPasswordThrowsAuthenticationException() {
        assertThrows(AuthenticationException.class,
                () -> service.login("alex", "wrongpass"));
    }

    @Test
    public void loginUnknownUserThrowsUserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> service.login("ghost", "secret1"));
    }

    @Test
    public void serviceUpdatesEmail() throws UserNotFoundException, InvalidUserDataException {
        service.updateEmail("maria", "maria.new@mail.ru");

        assertEquals("maria.new@mail.ru", service.find("maria").getEmail());
    }

    @Test
    public void serviceRejectsInvalidEmailOnUpdate() {
        InvalidUserDataException e = assertThrows(InvalidUserDataException.class,
                () -> service.updateEmail("maria", "maria[at]mail.ru"));

        assertEquals("email", e.getField());
    }

    @Test
    public void getAllReturnsUsersInRegistrationOrder() {
        assertEquals(2, service.getAll().size());
        assertEquals("alex", service.getAll().get(0).getUsername());
        assertEquals("maria", service.getAll().get(1).getUsername());
    }
}
