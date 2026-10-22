package by.epam.learn;

/**
 * Перечисление "Пол" (Gender).
 *
 * Задание 3, вариант 3: MALE, FEMALE. Перечисление является классом,
 * поэтому ему можно добавить собственное поле и переопределить toString().
 */
public enum Gender {

    MALE("мужской"),
    FEMALE("женский");

    private final String ru;

    Gender(String ru) {
        this.ru = ru;
    }

    public String getRu() {
        return ru;
    }

    @Override
    public String toString() {
        return ru;
    }
}
