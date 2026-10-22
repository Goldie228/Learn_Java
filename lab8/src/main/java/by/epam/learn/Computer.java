package by.epam.learn;

/**
 * Базовый класс "Компьютер" (Computer) — вариант 9 лабораторной работы №8.
 *
 * Содержит общие для всех компьютеров поля: процессор, объём ОЗУ и объём
 * накопителя. Наследники добавляют свои поля и переопределяют метод
 * {@link #getPerformance()}, а также расширяют {@link #displayInfo()}.
 */
public class Computer {

    private String processor;
    private int ram;
    private int storage;

    /**
     * @param processor модель процессора
     * @param ram       объём ОЗУ в ГБ
     * @param storage   объём накопителя в ГБ
     */
    public Computer(String processor, int ram, int storage) {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    /** Производительность обычного компьютера. Переопределяется наследниками. */
    public String getPerformance() {
        return "Базовая производительность";
    }

    /** Строка с общими характеристиками компьютера. */
    public String displayInfo() {
        return String.format("Процессор: %s, ОЗУ: %d ГБ, накопитель: %d ГБ",
                processor, ram, storage);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + displayInfo() + "}";
    }
}
