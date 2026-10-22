# Конспект: Лабораторная работа №13

**Тема:** Разработка, отладка и испытание программ с использованием обработки исключительных ситуаций.

---

## 1. Исключения и их иерархия

**Исключение** — событие, нарушающее нормальный ход программы. Корень —
`Throwable`: от него наследуются `Error` (системные ошибки) и `Exception`.
`Exception` делится на **checked** (`IOException` — компилятор требует
обработки) и **unchecked** (`RuntimeException`).

```java
public void read() throws IOException {
    Files.readAllLines(Paths.get("data.txt"));
}
```

## 2. try-catch-finally

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Ошибка: " + e.getMessage());
} finally {
    System.out.println("Выполняется всегда");
}
```

`try` — опасный код, `catch` — обработка, `finally` — выполняется всегда.

## 3. throw и throws

`throw` создаёт исключение, `throws` объявляет его в сигнатуре метода.

```java
public void withdraw(double amount) throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException("Недостаточно средств");
    }
}
```

## 4. Собственные исключения

```java
public class InvalidUserDataException extends Exception {
    private final String field;
    public InvalidUserDataException(String field, String message) {
        super(message);
        this.field = field;
    }
    public String getField() { return field; }
}
```

Своё исключение хранит контекст (поле, имя, номер попытки) и понятное сообщение.

## 5. Multi-catch

```java
try {
    int a = Integer.parseInt(text);
    int b = 100 / a;
} catch (NumberFormatException | ArithmeticException e) {
    System.out.println("Ошибка: " + e.getMessage());
}
```

Обработчики идут от специфичного к общему.

## 6. try-with-resources

```java
try (BufferedWriter writer =
        Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
    writer.write("строка");
}
```

Ресурсы, реализующие `AutoCloseable`, закрываются автоматически.

## 7. Система пользователей

`UserNotFoundException` и `AuthenticationException` — checked-исключения:
после `login(...)` их обрабатывают отдельными `catch` или пробрасывают.
Пароль не выдаётся геттером: доступна только проверка `checkPassword()`.
