/**
 * Класс Employee — сотрудник с ФИО и зарплатой
 * Задание 3: Лабораторная работа №3
 */
public class Employee {
    private String fullname;
    private double salary;

    public Employee(String fullname, double salary) {
        this.fullname = fullname;
        this.salary = salary;
    }

    public String getFullname() {
        return fullname;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return fullname + " — " + String.format("%.2f", salary);
    }
}
