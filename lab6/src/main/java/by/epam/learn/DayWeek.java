package by.epam.learn;

/**
 * Пример из теоретических сведений лабораторной работы №6.
 *
 * Класс реализует день недели. Скрытое ({@code private}) поле {@code value}
 * доступно только через методы {@link #Get()}, {@link #Set(int)} и
 * {@link #GetName()}. Метод {@code Set} проверяет корректность значения
 * (1..7), поэтому задать «несуществующий» день нельзя.
 */
class DayWeek {

    private int value; // скрытая переменная класса

    /** Конструктор без модификатора: доступен в пределах пакета. */
    DayWeek() {
        value = 1; // по умолчанию — "Monday"
    }

    /** @return текущее значение дня недели */
    public int Get() {
        return value;
    }

    /**
     * Устанавливает новый день недели.
     *
     * @param newValue значение в границах 1..7; иначе значение не меняется
     */
    public void Set(int newValue) {
        if ((newValue >= 1) && (newValue <= 7)) {
            value = newValue;
        }
    }

    /** @return название дня недели, соответствующее значению value */
    public String GetName() {
        String day = "Monday";
        switch (value) {
            case 2: day = "Tuesday"; break;
            case 3: day = "Wednesday"; break;
            case 4: day = "Thursday"; break;
            case 5: day = "Friday"; break;
            case 6: day = "Saturday"; break;
            case 7: day = "Sunday"; break;
            default: day = "Monday";
        }
        return day;
    }
}
