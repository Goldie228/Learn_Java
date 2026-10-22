package by.epam.learn;

/**
 * Класс "Студент" (Student) — задание 3 лабораторной работы №7.
 *
 * Нестатические поля: name, course, group — состояние конкретного студента.
 * Статические поля: totalStudents (счётчик), totalCourses (сумма курсов) —
 * общая статистика всех созданных объектов класса.
 *
 * Статический метод {@link #getAverageCourse()} считает средний курс и
 * вызывается через имя класса — создавать объект для него не нужно.
 */
public class Student {

    private String name;
    private int course;
    private String group;

    private static int totalStudents;
    private static int totalCourses;

    /**
     * Конструктор увеличивает оба счётчика класса.
     *
     * @param name   имя студента
     * @param course текущий курс (1..N)
     * @param group  группа
     */
    public Student(String name, int course, String group) {
        this.name = name;
        this.course = course;
        this.group = group;
        totalStudents++;
        totalCourses += course;
    }

    // --- Обычные геттеры (состояние объекта) ---

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }

    public String getGroup() {
        return group;
    }

    // ----------------------------------

    public static void test1() {
        System.out.println("Статические поля класса Student:");
        System.out.println("  totalStudents = " + totalStudents);
        System.out.println("  totalCourses  = " + totalCourses);
    }

    public void test2() {
        System.out.println("Поля объекта Student:");
        System.out.println("  name   = " + name);
        System.out.println("  course = " + course);
        System.out.println("  group  = " + group);
        System.out.println("Статические поля класса Student:");
        System.out.println("  totalStudents = " + totalStudents);
        System.out.println("  totalCourses  = " + totalCourses);
    }

    /**
     Student.test1();
     Student st1 = new Student("Mike", 3, "T-393");
     st1.test2();
     */

    // --- Доступ к статистике класса ---

    public static int getTotalStudents() {
        return totalStudents;
    }

    public static int getTotalCourses() {
        return totalCourses;
    }

    /**
     * Статический метод: среднее арифметическое курсов всех студентов.
     * Вызывается как {@code Student.getAverageCourse()}.
     *
     * @return средний курс, 0.0 если студентов ещё нет
     */
    public static double getAverageCourse() {
        if (totalStudents == 0) {
            return 0.0;
        }
        return (double) totalCourses / totalStudents;
    }

    /** Обнуляет общую статистику класса (для новых запусков и тестов). */
    public static void resetStatistics() {
        totalStudents = 0;
        totalCourses = 0;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %d курс | группа %s", name, course, group);
    }
}
