package by.epam.learn;

/** Отвёртка: дополнительно хранит тип жала. */
public class Screwdriver extends Tool {

    private String tipType;

    public Screwdriver(String name, int id, double weight, String tipType) {
        super(name, id, weight);
        this.tipType = tipType;
    }

    public String getTipType() {
        return tipType;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + ", тип жала: " + tipType;
    }
}
