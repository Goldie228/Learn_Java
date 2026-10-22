package by.epam.learn;

/**
 * Пример из теории: двумерная точка.
 *
 * Конструктор вызывает {@code super(x)}, чтобы инициализировать поле
 * суперкласса, затем задаёт собственное поле {@code y}.
 */
public class Point2D extends Point1D {

    private int y;

    public Point2D(int x, int y) {
        super(x);
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Point2D{x=%d, y=%d}", getX(), y);
    }
}
