import java.util.Scanner;
import java.util.Random;

/**
 * Лабораторная работа №2 — Вариант 3
 * Массивы: сумма элементов и сумма побочной диагонали
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Сумма всех элементов массива (Задание 1.3)");
            System.out.println("2. Сумма побочной диагонали матрицы (Задание 2.3)");
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

    /** Задание 1.3 — Найти сумму всех элементов массива */
    static void task1(Scanner sc) {
        System.out.println("\n--- Задание 1.3: Сумма элементов массива ---");

        System.out.print("Введите размер массива: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Введите " + n + " элементов:");
        for (int i = 0; i < n; i++) {
            System.out.print("  arr[" + i + "] = ");
            arr[i] = sc.nextInt();
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        System.out.print("Массив: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("Сумма всех элементов: " + sum);
    }

    /** Задание 2.3 — Найти сумму элементов побочной диагонали */
    static void task2(Scanner sc) {
        System.out.println("\n--- Задание 2.3: Сумма побочной диагонали ---");

        System.out.print("Введите размер квадратной матрицы N: ");
        int n = sc.nextInt();

        int[][] matrix = new int[n][n];
        System.out.println("Введите элементы матрицы " + n + "x" + n + ":");

        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("  matrix[" + i + "][" + j + "] = ");
               // matrix[i][j] = sc.nextInt();
               matrix[i][j] = rand.nextInt(100);
            }
        }

        System.out.println("\nМатрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%5d", matrix[i][j]);
            }
            System.out.println();
        }

        int sum = 0;
        System.out.print("Побочная диагональ: ");
        for (int i = 0; i < n; i++) {
            int val = matrix[i][n - 1 - i];
            sum += val;
            System.out.print(val);
            if (i < n - 1) System.out.print(" + ");
        }

        System.out.println(" = " + sum);
    }
}
