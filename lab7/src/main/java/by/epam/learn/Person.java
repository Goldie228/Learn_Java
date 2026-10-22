package by.epam.learn;

/**
 * Пример из теоретических сведений лабораторной работы №7.
 *
 * Класс Person (человек) демонстрирует статическое поле {@code retirementAge}
 * (пенсионный возраст) и статический инициализатор.
 *
 * Порядок при первом обращении к классу:
 * сначала выполняется статический инициализатор ("Static initializer"),
 * затем конструктор каждого объекта ("Constructor").
 */
class Person {

    int age;                      // обычное поле (состояние объекта)
    static int retirementAge;     // статическое поле (состояние класса)

    static {
        retirementAge = 65;
        System.out.println("Static initializer");
    }

    Person(int age) {
        this.age = age;
        System.out.println("Constructor");
    }

    void checkAge() {
        if (age >= retirementAge) {
            System.out.println("Уже на пенсии");
        } else {
            System.out.printf("Сколько лет осталось до пенсии: %d%n", retirementAge - age);
        }
    }
}
