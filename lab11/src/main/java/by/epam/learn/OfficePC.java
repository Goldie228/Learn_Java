package by.epam.learn;

/**
 * Офисный компьютер: переопределяет производительность и добавляет
 * собственный перегруженный метод запуска программы в окне.
 */
public class OfficePC extends Computer {

    private double monitorSize;

    public OfficePC(String model, int ram, int storage, double monitorSize) {
        super(model, ram, storage);
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
        return "Средняя";
    }

    /** Собственная перегрузка наследника: программа и режим окна. */
    public String run(String program, boolean minimized) {
        return "Запускает " + program + (minimized ? " в свёрнутом окне" : " в обычном окне");
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + ", монитор: " + monitorSize + " дюймов";
    }
}
