package by.epam.learn;

/** Молоток: дополнительно хранит длину рукоятки. */
public class Hammer extends Tool {

    private double handleLength;

    public Hammer(String name, int id, double weight, double handleLength) {
        super(name, id, weight);
        this.handleLength = handleLength;
    }

    public double getHandleLength() {
        return handleLength;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + ", длина рукоятки: " + handleLength + " см";
    }
}
