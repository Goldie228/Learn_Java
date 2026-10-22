/**
 * Класс Report — генерация отчёта по сотрудникам
 * Задание 3: Лабораторная работа №3
 */
public class Report {

    /**
     * Генерирует отчёт о зарплате всех сотрудников
     * Форматирование: фамилия — 15 символов, зарплата — 2 знака после запятой, выравнивание вправо
     */
    public static void generateReport(Employee[] employees) {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("           ОТЧЁТ ПО ЗАРПЛАТАМ");
        System.out.println("==========================================");
        System.out.printf("%-15s %10s%n", "ФАМИЛИЯ", "ЗАРПЛАТА");
        System.out.println("------------------------------------------");

        for (Employee emp : employees) {
            System.out.printf("%-15s %10.2f%n", emp.getFullname(), emp.getSalary());
        }

        System.out.println("------------------------------------------");

        double total = 0;
        for (Employee emp : employees) {
            total += emp.getSalary();
        }
        double average = total / employees.length;

        System.out.printf("%-15s %10.2f%n", "ИТОГО:", total);
        System.out.printf("%-15s %10.2f%n", "СРЕДНЯЯ:", average);
        System.out.println("==========================================");
    }
}
