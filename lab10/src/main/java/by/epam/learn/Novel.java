package by.epam.learn;

/**
 * Роман — художественная книга, которую можно читать и выдавать.
 */
public class Novel implements Readable, Lendable {

    private final String title;
    private final String author;
    private final int pages;
    private boolean available = true;

    public Novel(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int getPages() {
        return pages;
    }

    @Override
    public String read() {
        return "Читаю роман «" + title + "»";
    }

    @Override
    public boolean lend() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook() {
        if (!available) {
            available = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Novel{title='" + title + "', author='" + author + "', pages=" + pages + "}";
    }
}
