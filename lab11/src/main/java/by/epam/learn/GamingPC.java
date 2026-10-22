package by.epam.learn;

/**
 * Игровой компьютер: переопределяет производительность и добавляет
 * собственный перегруженный метод запуска игры.
 */
public class GamingPC extends Computer {

    private String videoCard;

    public GamingPC(String model, int ram, int storage, String videoCard) {
        super(model, ram, storage);
        this.videoCard = videoCard;
    }

    public String getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(String videoCard) {
        this.videoCard = videoCard;
    }

    @Override
    public String getPerformance() {
        return "Высокая";
    }

    /** Собственная перегрузка наследника: игра и частота кадров. */
    public String run(String game, int fps) {
        return "Запускает игру " + game + " с " + fps + " FPS";
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + ", видеокарта: " + videoCard;
    }
}
