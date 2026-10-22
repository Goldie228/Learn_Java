package by.epam.learn;

/**
 * Пример из теоретических сведений: одномерная точка.
 *
 * Поле {@code x} закрытое, доступно через геттер. Класс служит вершиной
 * цепочки наследования Point1D → Point2D → Point3D.
 */
public class Point1D {

    private int x;

    public Point1D(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return String.format("Point1D{x=%d}", x);
    }
}
