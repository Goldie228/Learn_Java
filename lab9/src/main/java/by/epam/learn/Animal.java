package by.epam.learn;

/**
 * Пример из теоретических сведений лабораторной работы №9.
 *
 * Абстрактный класс Animal содержит конструктор с параметрами, поэтому
 * наследник обязан вызвать его через {@code super(...)}.
 */
abstract class Animal {

    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Конструктор Animal вызван");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String makeSound();
}
