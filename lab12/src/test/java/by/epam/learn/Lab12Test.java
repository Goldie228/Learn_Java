package by.epam.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Модульные тесты обобщённых классов и коллекций (лабораторная работа №12).
 * Запускаются на фазе {@code mvn test}.
 */
public class Lab12Test {

    private static Hotel hotel(int id, String name, int stars, Integer... ratings) {
        return new Hotel(id, name, "Тестовый город", stars, Arrays.asList(ratings));
    }

    private static List<String> names(List<Hotel> hotels) {
        List<String> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            result.add(hotel.getName());
        }
        return result;
    }

    // ---------- Hotel: средний рейтинг ----------

    @Test
    public void averageRatingOfNonEmptyList() {
        Hotel hotel = hotel(1, "Тест", 4, 5, 4, 4);
        assertEquals(13.0 / 3.0, hotel.getAverageRating(), 0.0001);
    }

    @Test
    public void averageRatingOfEmptyListIsZero() {
        Hotel hotel = hotel(1, "Тест", 3);
        assertTrue(hotel.getGuestRatings().isEmpty());
        assertEquals(0.0, hotel.getAverageRating(), 0.0001);
    }

    @Test
    public void averageRatingIsRoundedCorrectlyForOutput() {
        Hotel hotel = hotel(1, "Тест", 4, 5, 4, 4);
        assertEquals(4.33, hotel.getAverageRating(), 0.01);
        assertEquals(4.5, hotel(2, "Другой", 4, 5, 4).getAverageRating(), 0.0001);
        assertEquals(4.0, hotel(3, "Третий", 4, 4, 5, 3).getAverageRating(), 0.0001);
    }

    // ---------- Hotel: валидация ----------

    @Test
    public void setStarsRejectsValuesOutOfRange() {
        Hotel hotel = hotel(1, "Тест", 3);
        assertThrows(IllegalArgumentException.class, () -> hotel.setStars(0));
        assertThrows(IllegalArgumentException.class, () -> hotel.setStars(6));
        assertEquals(3, hotel.getStars());

        hotel.setStars(5);
        assertEquals(5, hotel.getStars());
    }

    @Test
    public void addRatingRejectsValuesOutOfRange() {
        Hotel hotel = hotel(1, "Тест", 3);
        assertThrows(IllegalArgumentException.class, () -> hotel.addRating(0));
        assertThrows(IllegalArgumentException.class, () -> hotel.addRating(6));
        assertEquals(0.0, hotel.getAverageRating(), 0.0001);

        hotel.addRating(4);
        hotel.addRating(5);
        assertEquals(4.5, hotel.getAverageRating(), 0.0001);
    }

    @Test
    public void constructorDefensivelyCopiesRatings() {
        List<Integer> source = new ArrayList<>(List.of(5, 4));
        Hotel hotel = new Hotel(1, "Тест", "Город", 4, source);

        source.add(1);
        source.set(0, 1);

        assertEquals(2, hotel.getGuestRatings().size());
        assertEquals(4.5, hotel.getAverageRating(), 0.0001);
        assertThrows(UnsupportedOperationException.class,
                () -> hotel.getGuestRatings().add(1));
    }

    // ---------- HotelService: фильтрация и сортировка ----------

    @Test
    public void sortedByRatingDescendingWithCaseInsensitiveTie() {
        Hotel best = hotel(1, "Гранд Отель", 5, 5, 5, 4);
        Hotel tieFirst = hotel(2, "Астория", 4, 5, 4);
        Hotel tieSecond = hotel(3, "бригантина", 4, 5, 4);
        Hotel tieThird = hotel(4, "Волна", 4, 5, 4);
        Hotel worst = hotel(5, "Турист", 3, 3, 3, 3);

        List<Hotel> sorted = new HotelService()
                .sortedByRating(List.of(tieSecond, worst, tieThird, best, tieFirst));

        assertEquals(List.of("Гранд Отель", "Астория", "бригантина", "Волна", "Турист"),
                names(sorted));
    }

    @Test
    public void sortedByRatingDoesNotMutateSourceList() {
        Hotel first = hotel(1, "Первый", 4, 3);
        Hotel second = hotel(2, "Второй", 4, 5);
        List<Hotel> source = new ArrayList<>(List.of(first, second));

        List<Hotel> sorted = new HotelService().sortedByRating(source);

        assertSame(first, source.get(0));
        assertEquals("Второй", sorted.get(0).getName());
    }

    @Test
    public void lowRatedExcludesBoundaryValueThree() {
        Hotel below = hotel(1, "Ниже", 2, 2, 3);
        Hotel boundary = hotel(2, "Ровно три", 3, 3, 3, 3);
        Hotel above = hotel(3, "Выше", 4, 4, 3);
        Hotel empty = hotel(4, "Без отзывов", 3);

        List<Hotel> result = new HotelService()
                .lowRated(List.of(below, boundary, above, empty));

        assertEquals(List.of("Ниже", "Без отзывов"), names(result));
        assertFalse(result.contains(boundary));
    }

    @Test
    public void raiseStarsForBestUsesThresholdAndCap() {
        Hotel capped = hotel(1, "Пять звёзд", 5, 5, 5, 4);
        Hotel raised = hotel(2, "Три звезды", 3, 5, 4);
        Hotel below = hotel(3, "Ровно три", 3, 3, 3, 3);
        Hotel low = hotel(4, "Низкий", 1, 2, 2);

        new HotelService().raiseStarsForBest(List.of(capped, raised, below, low));

        assertEquals(5, capped.getStars());
        assertEquals(4, raised.getStars());
        assertEquals(3, below.getStars());
        assertEquals(1, low.getStars());
    }

    @Test
    public void byStarsFiltersHotels() {
        Hotel two = hotel(1, "Две звезды", 2, 4);
        Hotel three = hotel(2, "Три звезды", 3, 4);
        Hotel fourA = hotel(3, "Четыре звезды A", 4, 4);
        Hotel fourB = hotel(4, "Четыре звезды B", 4, 5);

        List<Hotel> result = new HotelService()
                .byStars(List.of(two, three, fourA, fourB), 4);

        assertEquals(List.of("Четыре звезды A", "Четыре звезды B"), names(result));
        assertEquals(0, new HotelService().byStars(List.of(two, three), 5).size());
    }

    // ---------- Map<String, Tool> ----------

    @Test
    public void toolsMapHasEightItemsWithUniqueKeys() {
        Map<String, Tool> tools = Main.createToolsMap();

        assertEquals(8, tools.size());
        assertEquals(8, tools.keySet().size());

        int hammers = 0;
        int screwdrivers = 0;
        int wrenches = 0;
        for (Tool tool : tools.values()) {
            if (tool instanceof Hammer) {
                hammers++;
            } else if (tool instanceof Screwdriver) {
                screwdrivers++;
            } else if (tool instanceof Wrench) {
                wrenches++;
            }
        }
        assertEquals(3, hammers);
        assertEquals(3, screwdrivers);
        assertEquals(2, wrenches);
        assertTrue(tools.containsKey("Молоток слесарный"));
    }

    @Test
    public void repeatedKeyOverwritesValue() {
        Map<String, Tool> demo = new LinkedHashMap<>();
        Tool first = new Wrench("Напильник", 401, 0.30, 10);
        Tool second = new Screwdriver("Напильник", 402, 0.10, "PH1");

        demo.put("Напильник", first);
        demo.put("Напильник", second);

        assertEquals(1, demo.size());
        assertSame(second, demo.get("Напильник"));
        assertTrue(demo.get("Напильник") instanceof Screwdriver);
    }

    @Test
    public void toolDisplayInfoIncludesSubclassDetails() {
        String hammerInfo = new Hammer("Молоток", 1, 1.0, 30.0).displayInfo();
        assertTrue(hammerInfo.contains("Молоток"));
        assertTrue(hammerInfo.contains("id=1"));
        assertTrue(hammerInfo.contains("длина рукоятки: 30.0 см"));
        assertTrue(new Screwdriver("Отвёртка", 2, 0.1, "PH2")
                .displayInfo().contains("тип жала: PH2"));
        assertTrue(new Wrench("Ключ", 3, 0.4, 17)
                .displayInfo().contains("размер: 17 мм"));
    }

    // ---------- Обобщённые классы ----------

    @Test
    public void boxAndPairKeepValuesAndTypes() {
        Box<String> stringBox = new Box<>("Hello");
        Box<Integer> intBox = new Box<>(123);
        Pair<String, Integer> pair = new Pair<>("Возраст", 25);

        assertEquals("Hello", stringBox.getValue());
        assertEquals(Integer.valueOf(123), intBox.getValue());
        assertTrue(stringBox.getValue() instanceof String);
        assertEquals("Возраст", pair.getKey());
        assertEquals(Integer.valueOf(25), pair.getValue());

        stringBox.setValue("World");
        assertEquals("World", stringBox.getValue());
        assertEquals("Pair[Возраст = 25]", pair.toString());
    }
}
