package by.epam.learn;

/**
 * Офисный компьютер (OfficePC) — наследник {@link Computer}.
 *
 * Добавляет поле "размер монитора", переопределяет производительность и
 * предоставляет уникальный метод {@link #printDocument()}.
 */
public class OfficePC extends Computer {

    private double monitorSize;

    public OfficePC(String processor, int ram, int storage, double monitorSize) {
        super(processor, ram, storage);
        this.monitorSize = monitorSize;
    }

    public double getMonitorSize() {
        return monitorSize;
    }

    public void setMonitorSize(double monitorSize) {
        this.monitorSize = monitorSize;
    }

    @Override
    public String getPerformance() {
        return "Средняя производительность";
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + String.format(", монитор: %.1f дюймов", monitorSize);
    }

    /** Уникальное действие офисного компьютера. */
    public String printDocument() {
        return "Печатает документ";
    }
}
