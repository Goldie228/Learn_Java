package by.epam.learn;

import java.util.Scanner;

/**
 * Лабораторная работа №7 — Вариант 3.
 * Статические поля и методы: класс Student + пример Person из теории.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========== МЕНЮ ==========");
            System.out.println("1. Студенты: статистика (Задание 3)");
            System.out.println("2. Пример: статическое поле (Person)");
            System.out.println("3. Тест: вывод всех полей (test1 / test2)");
            System.out.println("0. Выход");
            System.out.println("==========================");
            System.out.print("Выберите действие: ");

            String choiceStr = sc.next();
            sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    task1();
                    break;
                case 2:
                    task2();
                    break;
                case 3: {
                    Student testStudent = new Student("Смирнов Иван", 2, "ИС-21");
                    System.out.println("\n--- Тест: вывод всех полей ---");
                    Student.test1();
                    testStudent.test2();
                    break;
                }
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

    /** Задание 3 — класс Student: создать 6 студентов и вывести средний курс. */
    static void task1() {
        System.out.println("\n--- Задание 3: Студенты ---");

        Student.resetStatistics();

        new Student("Алексеев Артём", 1, "ИС-21");
        new Student("Белова Полина", 2, "ИС-20");
        new Student("Волков Никита", 3, "ПО-19");
        new Student("Гришина Дарья", 4, "ИС-18");
        new Student("Дорохов Егор", 2, "ПО-20");
        new Student("Ефимова Алина", 1, "ИС-21");

        System.out.println("Создано студентов: " + Student.getTotalStudents());
        System.out.println("Сумма курсов:      " + Student.getTotalCourses());
        System.out.printf("Средний курс:      %.2f%n", Student.getAverageCourse());
    }

    /** Пример из теории: статическое поле retirementAge + статический инициализатор. */
    static void task2() {
        System.out.println("\n--- Пример: статическое поле (Person) ---");

        Person tom = new Person(41);
        tom.checkAge();

        Person bob = new Person(68);
        bob.checkAge();

        System.out.println("Person.retirementAge = " + Person.retirementAge);
        Person.retirementAge = 67;
        System.out.println("После Person.retirementAge = 67 → " + Person.retirementAge);
        Person.retirementAge = 65;
    }
}
