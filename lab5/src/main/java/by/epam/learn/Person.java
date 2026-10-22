package by.epam.learn;

/**
 * Класс "Человек" (Person).
 *
 * Задание 3, вариант 3: поле gender типа перечисления Gender.
 */
public class Person {

    private String name;
    private Gender gender;

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return String.format("%-10s — пол: %s", name, gender);
    }
}
