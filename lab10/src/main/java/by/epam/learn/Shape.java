package by.epam.learn;

/**
 * Фигура из теоретического примера: реализует сразу три интерфейса —
 * {@link Drawable}, {@link Moveable}, {@link Resizable}.
 */
public class Shape implements Drawable, Moveable, Resizable {

    private int x;
    private int y;
    private double size;

    public Shape(int x, int y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    @Override
    public void draw() {
        System.out.println("Рисую фигуру в точке (" + x + ", " + y + "), размер " + size);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        System.out.println("Перемещаю фигуру на (" + dx + ", " + dy + ") → (" + x + ", " + y + ")");
    }

    @Override
    public void resize(double factor) {
        size *= factor;
        System.out.println("Масштабирую фигуру с коэффициентом " + factor + " → размер " + size);
    }
}
