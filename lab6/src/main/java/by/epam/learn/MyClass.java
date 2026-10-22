package by.epam.learn;

/**
 * Пример из теоретических сведений лабораторной работы №6.
 *
 * Поле {@code s} объявлено {@code private}, поэтому напрямую извне недоступно.
 * Чтение — через геттер {@link #getS()}, запись — через сеттер {@link #setS(String)}.
 */
public class MyClass {

    private String s = "Hello World!";

    public String getS() {
        return s;
    }

    public void setS(String newValue) {
        s = newValue;
    }
}
