package by.epam.learn;

/**
 * Базовый класс «Компьютер». Демонстрирует перегрузку методов {@code run()}
 * и метод {@link #getPerformance()}, предназначенный для переопределения.
 */
public class Computer {

    private String model;
    private int ram;
    private int storage;

    public Computer(String model, int ram, int storage) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    /** Перегрузка 1: компьютер просто работает. */
    public String run() {
        return "Компьютер работает";
    }

    /** Перегрузка 2: запуск конкретной программы. */
    public String run(String program) {
        return "Запускает " + program;
    }

    /** Перегрузка 3: запуск нескольких процессов. */
    public String run(int processes) {
        return "Запускает " + processes + " процессов";
    }

    /** Производительность по умолчанию; переопределяется в наследниках. */
    public String getPerformance() {
        return "Средняя";
    }

    /** Вывод сведений о компьютере. */
    public String displayInfo() {
        return "Модель: " + model + ", ОЗУ: " + ram + " ГБ, накопитель: " + storage
                + " ГБ, производительность: " + getPerformance();
    }

    @Override
    public String toString() {
        return displayInfo();
    }
}
