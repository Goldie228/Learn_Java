package by.epam.learn;

/**
 * Пример из теории: трёхмерная точка.
 *
 * Основной конструктор вызывает {@code super(x, y)}, а конструктор по
 * умолчанию через {@code this(-1, -1, -1)} делегирует инициализацию
 * основному конструктору этого же класса.
 */
public class Point3D extends Point2D {

    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Point3D() {
        this(-1, -1, -1);
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("Point3D{x=%d, y=%d, z=%d}", getX(), getY(), z);
    }
}
