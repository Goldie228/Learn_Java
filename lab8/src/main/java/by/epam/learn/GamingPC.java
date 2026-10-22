package by.epam.learn;

/**
 * Игровой компьютер (GamingPC) — наследник {@link Computer}.
 *
 * Добавляет поле "видеокарта", переопределяет производительность и
 * предоставляет уникальный метод {@link #playGame()}.
 */
public class GamingPC extends Computer {

    private String videoCard;

    public GamingPC(String processor, int ram, int storage, String videoCard) {
        super(processor, ram, storage);
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
        return "Высокая производительность";
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + String.format(", видеокарта: %s", videoCard);
    }

    /** Уникальное действие игрового компьютера. */
    public String playGame() {
        return "Запускает современную игру";
    }
}
