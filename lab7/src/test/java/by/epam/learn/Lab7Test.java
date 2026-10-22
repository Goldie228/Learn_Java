package by.epam.learn;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Модульные тесты статических полей и методов класса {@link Student}
 * (лабораторная работа №7). Запускаются на фазе {@code mvn test}.
 */
public class Lab7Test {

    @Before
    public void resetStatistics() {
        Student.resetStatistics();
    }

    @Test
    public void countersIncreaseOnConstruction() {
        assertEquals(0, Student.getTotalStudents());
        assertEquals(0, Student.getTotalCourses());

        new Student("Иванов Иван", 2, "ИС-21");
        new Student("Петрова Мария", 3, "ПО-20");

        assertEquals(2, Student.getTotalStudents());
        assertEquals(5, Student.getTotalCourses());
    }

    @Test
    public void averageCourseOfSixStudents() {
        new Student("А", 1, "Г-1");
        new Student("Б", 2, "Г-1");
        new Student("В", 3, "Г-2");
        new Student("Г", 4, "Г-2");
        new Student("Д", 2, "Г-3");
        new Student("Е", 1, "Г-3");

        assertEquals(13.0 / 6.0, Student.getAverageCourse(), 0.0001);
    }

    @Test
    public void averageCourseWhenEmpty() {
        assertEquals(0.0, Student.getAverageCourse(), 0.0001);
    }

    @Test
    public void instanceStateDoesNotAffectCounters() {
        Student student = new Student("Иванов Иван", 3, "ИС-19");

        assertEquals("Иванов Иван", student.getName());
        assertEquals(3, student.getCourse());
        assertEquals("ИС-19", student.getGroup());
        assertEquals(1, Student.getTotalStudents());
        assertEquals(3, Student.getTotalCourses());
    }
}
