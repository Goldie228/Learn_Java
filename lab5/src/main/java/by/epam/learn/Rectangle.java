package by.epam.learn;

/**
 * Класс "Прямоугольник" (Rectangle).
 *
 * Задание 1, вариант 3: поля width и height (double), конструктор,
 * методы area() и perimeter().
 */
public class Rectangle {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /** @return площадь прямоугольника */
    public double area() {
        return width * height;
    }

    /** @return периметр прямоугольника */
    public double perimeter() {
        return 2 * (width + height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("%.2f x %.2f  ->  площадь = %.2f, периметр = %.2f",
                width, height, area(), perimeter());
    }
}
