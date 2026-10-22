package by.epam.learn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Операции над коллекцией отелей: фильтрация, изменение звёздности,
 * сортировка и печать.
 */
public class HotelService {

    /** Отели со средним рейтингом строго меньше 3. */
    public List<Hotel> lowRated(List<Hotel> hotels) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getAverageRating() < 3.0) {
                result.add(hotel);
            }
        }
        return result;
    }

    /** Повышает звёздность отелей с рейтингом не ниже 4, но не выше 5 звёзд. */
    public void raiseStarsForBest(List<Hotel> hotels) {
        for (Hotel hotel : hotels) {
            if (hotel.getAverageRating() >= 4.0) {
                hotel.setStars(Math.min(5, hotel.getStars() + 1));
            }
        }
    }

    /** Отели с заданным количеством звёзд. */
    public List<Hotel> byStars(List<Hotel> hotels, int stars) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getStars() == stars) {
                result.add(hotel);
            }
        }
        return result;
    }

    /**
     * Сортировка по убыванию среднего рейтинга; при равном рейтинге —
     * по названию в алфавитном порядке без учёта регистра.
     */
    public List<Hotel> sortedByRating(List<Hotel> hotels) {
        List<Hotel> result = new ArrayList<>(hotels);
        result.sort(Comparator
                .comparingDouble(Hotel::getAverageRating)
                .reversed()
                .thenComparing(Hotel::getName, String.CASE_INSENSITIVE_ORDER));
        return result;
    }

    /** Печать полной информации о каждом отеле. */
    public void printHotels(List<Hotel> hotels) {
        if (hotels.isEmpty()) {
            System.out.println("  (список пуст)");
            return;
        }
        for (Hotel hotel : hotels) {
            System.out.println("  " + hotel.displayInfo());
        }
    }

    /** Печать отелей с заданным количеством звёзд. */
    public void printHotels(List<Hotel> hotels, int stars) {
        System.out.println("Отели с " + stars + " звёздами:");
        printHotels(byStars(hotels, stars));
    }
}
