package by.epam.learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Отель с набором оценок гостей и вычисляемым средним рейтингом.
 */
public class Hotel {

    private int id;
    private String name;
    private String city;
    private int stars;
    private List<Integer> guestRatings;

    public Hotel(int id, String name, String city, int stars, List<Integer> guestRatings) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stars = stars;
        this.guestRatings = guestRatings == null
                ? new ArrayList<>()
                : new ArrayList<>(guestRatings);
        setStars(stars);
    }

    /** Средняя оценка гостей; для пустого списка возвращает 0.0. */
    public double getAverageRating() {
        if (guestRatings.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (int rating : guestRatings) {
            sum += rating;
        }
        return sum / guestRatings.size();
    }

    public int getStars() {
        return stars;
    }

    /** Количество звёзд допустимо только в диапазоне 1..5. */
    public void setStars(int stars) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException(
                    "Количество звёзд должно быть в диапазоне 1..5: " + stars);
        }
        this.stars = stars;
    }

    /** Оценка гостя допустима только в диапазоне 1..5. */
    public void addRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(
                    "Оценка должна быть в диапазоне 1..5: " + rating);
        }
        guestRatings.add(rating);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    /** Возвращает только читаемое представление, чтобы нельзя было изменить состояние извне. */
    public List<Integer> getGuestRatings() {
        return Collections.unmodifiableList(guestRatings);
    }

    public String displayInfo() {
        return String.format("ID: %d | %s | %s | %d★ | средний рейтинг: %.2f",
                id, name, city, stars, getAverageRating());
    }

    @Override
    public String toString() {
        return displayInfo();
    }
}
