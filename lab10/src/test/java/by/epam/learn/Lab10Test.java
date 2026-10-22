package by.epam.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Модульные тесты интерфейсов и классов лабораторной работы №10.
 */
public class Lab10Test {

    @Test
    public void novelReadMessageContainsTitle() {
        Novel novel = new Novel("Мастер и Маргарита", "М. А. Булгаков", 480);

        assertEquals("Читаю роман «Мастер и Маргарита»", novel.read());
        assertEquals("Мастер и Маргарита", novel.getTitle());
        assertEquals("М. А. Булгаков", novel.getAuthor());
    }

    @Test
    public void textbookReadMessageContainsSubject() {
        Textbook textbook = new Textbook("Информатика", "И. Н. Блинов", 560, "информатика");

        assertEquals("Читаю учебник по предмету «информатика»", textbook.read());
        assertEquals("информатика", textbook.getSubject());
    }

    @Test
    public void pagesAndDefaultReadingTime() {
        Readable novel = new Novel("1984", "Джордж Оруэлл", 320);
        Readable textbook = new Textbook("Алгебра", "А. Г. Мерзляк", 288, "математика");

        assertEquals(320, novel.getPages());
        assertEquals(640, novel.getReadingTimeMinutes());
        assertEquals(288, textbook.getPages());
        assertEquals(576, textbook.getReadingTimeMinutes());
    }

    @Test
    public void formatPagesIsStaticHelper() {
        assertEquals("100 стр.", Readable.formatPages(100));
        assertEquals("0 стр.", Readable.formatPages(0));
    }

    @Test
    public void lendAndReturnChangeAvailability() {
        Lendable book = new Novel("1984", "Джордж Оруэлл", 320);

        assertTrue(book.isAvailable());
        assertTrue(book.lend());
        assertFalse(book.isAvailable());
        assertTrue(book.returnBook());
        assertTrue(book.isAvailable());
    }

    @Test
    public void repeatedLendFailsWhileBookIsIssued() {
        Lendable book = new Textbook("Информатика", "И. Н. Блинов", 560, "информатика");

        assertTrue(book.lend());
        assertFalse(book.lend());
        assertFalse(book.isAvailable());
    }

    @Test
    public void repeatedReturnFailsForAvailableBook() {
        Lendable book = new Novel("Мастер и Маргарита", "М. А. Булгаков", 480);

        assertFalse(book.returnBook());
        assertTrue(book.isAvailable());

        assertTrue(book.lend());
        assertTrue(book.returnBook());
        assertFalse(book.returnBook());
        assertTrue(book.isAvailable());
    }

    @Test
    public void readableListWorksPolymorphically() {
        List<Readable> books = new ArrayList<>();
        books.add(new Novel("Мастер и Маргарита", "М. А. Булгаков", 480));
        books.add(new Novel("Преступление и наказание", "Ф. М. Достоевский", 672));
        books.add(new Novel("1984", "Джордж Оруэлл", 320));
        books.add(new Textbook("Информатика", "И. Н. Блинов", 560, "информатика"));
        books.add(new Textbook("Алгебра", "А. Г. Мерзляк", 288, "математика"));

        int totalPages = 0;
        for (Readable book : books) {
            totalPages += book.getPages();
            assertFalse(book.read().isEmpty());
            assertEquals(book.getPages() * 2, book.getReadingTimeMinutes());
            assertTrue(book instanceof Lendable);
        }

        assertEquals(5, books.size());
        assertEquals(2320, totalPages);
        assertEquals("2320 стр.", Readable.formatPages(totalPages));
    }

    @Test
    public void shapeImplementsThreeInterfacesAndChangesState() {
        Shape shape = new Shape(1, 2, 10.0);

        assertTrue(shape instanceof Drawable);
        assertTrue(shape instanceof Moveable);
        assertTrue(shape instanceof Resizable);

        shape.move(3, 4);
        assertEquals(4, shape.getX());
        assertEquals(6, shape.getY());

        shape.resize(1.5);
        assertEquals(15.0, shape.getSize(), 0.0001);

        Drawable drawable = shape;
        Moveable moveable = shape;
        Resizable resizable = shape;
        assertTrue(drawable instanceof Shape);
        assertTrue(moveable instanceof Shape);
        assertTrue(resizable instanceof Shape);
    }
}
