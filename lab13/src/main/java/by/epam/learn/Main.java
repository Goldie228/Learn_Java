package by.epam.learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Лабораторная работа №13 — Вариант 9.
 * Обработка исключительных ситуаций: система пользователей.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService service = new UserService();
        prepareDemoUsers(service);

        System.out.println("Демонстрационные пользователи: alex, maria");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(sc, "Выберите действие: ");

            switch (choice) {
                case 1:
                    task1(service);
                    break;
                case 2:
                    task2(service);
                    break;
                case 3:
                    task3(service);
                    break;
                case 4:
                    task4();
                    break;
                case 5:
                    printUsers(service);
                    break;
                case 0:
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========== МЕНЮ ==========");
        System.out.println("1. Регистрация пользователей (2 корректные + 2 некорректные)");
        System.out.println("2. Вход в систему (успех, неверный пароль, неизвестный пользователь)");
        System.out.println("3. Обновление email (корректный и некорректный)");
        System.out.println("4. Пример из теории: multi-catch и try-with-resources");
        System.out.println("5. Показать всех пользователей");
        System.out.println("0. Выход");
        System.out.println("==========================");
    }

    /** Создаёт пару пользователей, чтобы пункты 2 и 3 работали независимо. */
    private static void prepareDemoUsers(UserService service) {
        try {
            service.register(new User("alex", "alex@mail.ru", "secret1", 20));
            service.register(new User("maria", "maria@mail.by", "qwerty12", 22));
        } catch (InvalidUserDataException e) {
            System.out.println("Ошибка подготовки данных: " + e.getMessage());
        }
    }

    /** Пункт 1 — регистрация: два корректных и два некорректных случая. */
    private static void task1(UserService service) {
        System.out.println("\n--- Регистрация пользователей (пункт 1) ---");

        register(service, "ivan", "ivan@mail.ru", "ivan123", 21);
        register(service, "olga", "olga@mail.by", "olga2024", 23);
        register(service, "pavel", "pavel@mail", "pavel123", 19);
        register(service, "anna", "anna@mail.ru", "12345", 25);

        System.out.println("Всего зарегистрировано пользователей: " + service.size());
    }

    private static void register(UserService service, String username, String email,
                                 String password, int age) {
        System.out.println();
        System.out.println("Регистрация: " + username + " / " + email);
        try {
            User user = new User(username, email, password, age);
            service.register(user);
            System.out.println("Пользователь зарегистрирован: " + user.getUsername());
        } catch (InvalidUserDataException e) {
            System.out.println("Ошибка в поле " + e.getField() + ": " + e.getMessage());
        } finally {
            System.out.println("Операция регистрации завершена");
        }
    }

    /** Пункт 2 — вход: три отдельных сценария с собственными try-catch. */
    private static void task2(UserService service) {
        System.out.println("\n--- Вход в систему (пункт 2) ---");

        System.out.println("\nВход: alex / secret1");
        try {
            service.login("alex", "secret1");
            System.out.println("Вход выполнен: alex (" + service.find("alex").getEmail() + ")");
        } catch (UserNotFoundException e) {
            System.out.println("Пользователь не найден: " + e.getMessage());
        } catch (AuthenticationException e) {
            System.out.println("Ошибка аутентификации: " + e.getMessage());
        }

        System.out.println("\nВход: alex / wrongpass");
        try {
            service.login("alex", "wrongpass");
            System.out.println("Вход выполнен: alex");
        } catch (UserNotFoundException e) {
            System.out.println("Пользователь не найден: " + e.getMessage());
        } catch (AuthenticationException e) {
            System.out.println("Ошибка аутентификации: " + e.getMessage());
        }

        System.out.println("\nВход: ghost / secret1");
        try {
            service.login("ghost", "secret1");
            System.out.println("Вход выполнен: ghost");
        } catch (UserNotFoundException e) {
            System.out.println("Пользователь не найден: " + e.getMessage());
        } catch (AuthenticationException e) {
            System.out.println("Ошибка аутентификации: " + e.getMessage());
        }
    }

    /** Пункт 3 — обновление email: корректный и некорректный адреса. */
    private static void task3(UserService service) {
        System.out.println("\n--- Обновление email (пункт 3) ---");

        System.out.println("\nalex: новый email alex.new@mail.ru");
        try {
            service.updateEmail("alex", "alex.new@mail.ru");
            System.out.println("Email обновлён: " + service.find("alex").getEmail());
        } catch (UserNotFoundException e) {
            System.out.println("Пользователь не найден: " + e.getMessage());
        } catch (InvalidUserDataException e) {
            System.out.println("Ошибка в поле " + e.getField() + ": " + e.getMessage());
        }

        System.out.println("\nmaria: новый email maria[at]mail.ru");
        try {
            service.updateEmail("maria", "maria[at]mail.ru");
            System.out.println("Email обновлён: " + service.find("maria").getEmail());
        } catch (UserNotFoundException e) {
            System.out.println("Пользователь не найден: " + e.getMessage());
        } catch (InvalidUserDataException e) {
            System.out.println("Ошибка в поле " + e.getField() + ": " + e.getMessage());
        }
    }

    /** Пункт 4 — примеры из теории: multi-catch, finally, try-with-resources. */
    private static void task4() {
        System.out.println("\n--- Пример из теории (пункт 4) ---");

        try {
            int value = Integer.parseInt("не число");
            System.out.println("Результат: " + value);
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Multi-catch, " + e.getClass().getSimpleName()
                    + ": " + e.getMessage());
        } finally {
            System.out.println("Блок finally выполнен");
        }

        try {
            int result = 100 / 0;
            System.out.println("Результат: " + result);
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Multi-catch, " + e.getClass().getSimpleName()
                    + ": " + e.getMessage());
        } finally {
            System.out.println("Блок finally выполнен");
        }

        Path dir = Paths.get("data");
        Path file = dir.resolve("theory.txt");
        List<String> lines = List.of(
                "try-catch-finally",
                "multi-catch: NumberFormatException | ArithmeticException",
                "try-with-resources закрывает поток автоматически");

        try {
            Files.createDirectories(dir);
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            System.out.println("Файл " + file + " записан и прочитан:");
            try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
                String line;
                int number = 1;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Строка " + number++ + ": " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }

    private static void printUsers(UserService service) {
        System.out.println("\n--- Пользователи (" + service.size() + ") ---");
        for (User user : service.getAll()) {
            System.out.println(user.getUsername() + " | " + user.getEmail()
                    + " | возраст: " + user.getAge());
        }
    }

    /** Читает целое число, повторяя запрос при ошибке ввода. */
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNext()) {
                return 0;
            }
            String token = sc.next();
            try {
                return Integer.parseInt(token);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }
}
