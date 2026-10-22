package by.epam.learn;

/**
 * Пример класса из теоретических сведений лабораторной работы №5.
 *
 * Описывает характеристики и поведение кота: атрибуты (имя, вес, окрас)
 * и методы (есть, спать, говорить).
 */
public class Cat {

    private int weight;    // вес кота
    private String name;   // имя кота
    private String color;  // окрас кота

    public Cat() {
    }

    public Cat(String name, int weight, String color) {
        this.name = name;
        this.weight = weight;
        this.color = color;
    }

    /** Кот ест. */
    public void eat() {
        System.out.print("Eating...\n");
    }

    /** Кот спит. */
    public void sleep() {
        System.out.print("Sleeping zz-z-z-z...\n");
    }

    /**
     * Кот говорит.
     *
     * @param words то, что говорит кот
     * @return фраза с добавленным "...mauu..."
     */
    public String speak(String words) {
        return words + "...mauu...\n";
    }

    @Override
    public String toString() {
        return name + " (" + color + ", " + weight + " кг)";
    }
}
