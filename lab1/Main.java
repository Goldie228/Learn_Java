import java.util.Scanner;

/**
 * Лабораторная работа №1 — Вариант 3
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Сумма двух чисел (Задание 1.3)");
            System.out.println("2. Модуль числа (Задание 2.3)");
            System.out.println("3. Чётные числа от 2 до 100 (Задание 3.3)");
            System.out.println("0. Выход");
            System.out.println("==========================");
            System.out.print("Выберите действие: ");

            int choice = sc.nextInt();

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

    /** Задание 1.3 — Ввести два числа, вывести сумму */
    static void task1(Scanner sc) {
        System.out.println("\n--- Задание 1.3: Сумма двух чисел ---");

        System.out.print("Введите первое число: ");
        double a = sc.nextDouble();

        System.out.print("Введите второе число: ");
        double b = sc.nextDouble();

        double sum = a + b;
        System.out.println("Сумма: " + a + " + " + b + " = " + sum);
    }

    /** Задание 2.3 — Найти модуль числа без Math.abs() */
    static void task2(Scanner sc) {
        System.out.println("\n--- Задание 2.3: Модуль числа ---");

        System.out.print("Введите число: ");
        double number = sc.nextDouble();

        double absValue;
        if (number < 0) {
            absValue = -number;
        } else {
            absValue = number;
        }

        System.out.println("|" + number + "| = " + absValue);
    }

    /** Задание 3.3 — Вывести все чётные числа от 2 до 100 */
    static void task3() {
        System.out.println("\n--- Задание 3.3: Чётные числа от 2 до 100 ---");

        int count = 0;
        int i = 2;

        while (i <= 100) {
            System.out.printf("%4d", i);
            count++;
            if (count % 10 == 0) {
                System.out.println();
            }

            i += 2;
        }

        System.out.println();
        System.out.println("Всего чётных чисел: " + count);
    }
}
