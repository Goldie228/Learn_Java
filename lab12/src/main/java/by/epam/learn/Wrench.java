package by.epam.learn;

/** Гаечный ключ: дополнительно хранит размер зева. */
public class Wrench extends Tool {

    private int size;

    public Wrench(String name, int id, double weight, int size) {
        super(name, id, weight);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + ", размер: " + size + " мм";
    }
}
