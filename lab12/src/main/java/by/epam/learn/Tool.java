package by.epam.learn;

/**
 * Базовый класс инструмента: общие поля имени, идентификатора и веса.
 */
public class Tool {

    private String name;
    private int id;
    private double weight;

    public Tool(String name, int id, double weight) {
        this.name = name;
        this.id = id;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    /** Строковое представление общих характеристик инструмента. */
    public String displayInfo() {
        return String.format("%s (id=%d, вес=%.2f кг)", name, id, weight);
    }

    @Override
    public String toString() {
        return displayInfo();
    }
}
