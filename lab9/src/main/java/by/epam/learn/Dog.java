package by.epam.learn;

/**
 * Пример из теории: конкретный наследник абстрактного класса Animal.
 *
 * Сначала выполняется конструктор суперкласса (вызов {@code super}),
 * затем — конструктор наследника. Именно поэтому сообщение
 * "Конструктор Animal вызван" появляется раньше "Конструктор Dog вызван".
 */
class Dog extends Animal {

    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
        System.out.println("Конструктор Dog вызван");
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String makeSound() {
        return "Гав-гав!";
    }
}
