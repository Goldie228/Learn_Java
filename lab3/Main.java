import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Лабораторная работа №3 — Вариант 3
 * Строки: последний символ, самое короткое слово, Employee/Report, regex
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Последний символ строки (Задание 1.3)");
            System.out.println("2. Самое короткое слово (Задание 2.3)");
            System.out.println("3. Отчёт по сотрудникам (Задание 3)");
            System.out.println("4. Regex: слова из 'a' и букв/цифр (Задание 4.3)");
            System.out.println("0. Выход");
            System.out.println("==========================");
            System.out.print("Выберите действие: ");

            String choiceStr = sc.next();
            sc.nextLine(); // consume remaining newline
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    task1(sc);
                    break;
                case 2:
                    task2(sc);
                    break;
                case 3:
                    task3();
                    break;
                case 4:
                    task4();
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

    /** Задание 1.3 — Вывести последний символ строки */
    static void task1(Scanner sc) {
        System.out.println("\n--- Задание 1.3: Последний символ строки ---");

        System.out.print("Введите строку: ");
        String str = sc.nextLine();

        if (str.isEmpty()) {
            System.out.println("Строка пуста!");
            return;
        }

        char lastChar = str.charAt(str.length() - 1);
        System.out.println("Строка: \"" + str + "\"");
        System.out.println("Длина строки: " + str.length());
        System.out.println("Последний символ: '" + lastChar + "'");
    }

    /** Задание 2.3 — Найти самое короткое слово в предложении */
    static void task2(Scanner sc) {
        System.out.println("\n--- Задание 2.3: Самое короткое слово ---");

        System.out.print("Введите предложение: ");
        String sentence = sc.nextLine();

        String[] words = sentence.split("\\s+");

        String shortest = words[0];
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() < shortest.length()) {
                shortest = words[i];
            }
        }

        System.out.println("Все слова: ");
        for (int i = 0; i < words.length; i++) {
            System.out.println("  " + words[i] + " (" + words[i].length() + " симв.)");
        }
        System.out.println("Самое короткое слово: \"" + shortest + "\" (" + shortest.length() + " симв.)");
    }

    /** Задание 3 — Employee + Report (отчёт по зарплатам) */
    static void task3() {
        System.out.println("\n--- Задание 3: Отчёт по сотрудникам ---");

        Employee[] employees = {
            new Employee("Иванов", 1250.75),
            new Employee("Петрова", 980.50),
            new Employee("Сидоров", 1500.00),
            new Employee("Козлова", 1100.30),
            new Employee("Новиков", 1350.00)
        };

        Report.generateReport(employees);
    }

    /** Задание 4.3 — Regex: по краям 'a', между ними — маленькие латинские буквы и цифры */
    static void task4() {
        System.out.println("\n--- Задание 4.3: Regex ---");
        System.out.println("Правило: по краям 'a', между ними — маленькие латинские буквы и цифры");

        String text = "aAXa aeffa aGha aza ax23a a3sSa";
        System.out.println("Исходная строка: " + text);
        System.out.println();

        // a — буква a, [a-z0-9]+ — одна или более маленьких латинских букв или цифр, a — буква a
        Pattern pattern = Pattern.compile("\\ba[a-z0-9]+a\\b");
        Matcher matcher = pattern.matcher(text);

        System.out.println("Результат:");
        boolean found = false;
        while (matcher.find()) {
            System.out.println("  Найдено: \"" + matcher.group() + "\" (индекс " + matcher.start() + ")");
            found = true;
        }

        if (!found) {
            System.out.println("  Совпадений не найдено.");
        }
    }
}
