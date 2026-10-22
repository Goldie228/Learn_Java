package by.epam.learn;

/** Обобщённый контейнер для одного значения произвольного типа. */
public class Box<T> {

    private T value;

    public Box() {
    }

    public Box(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Box[" + value + "]";
    }
}
