package by.epam.learn;

/**
 * Учебник — книга по определённому предмету, которую можно читать и выдавать.
 */
public class Textbook implements Readable, Lendable {

    private final String title;
    private final String author;
    private final int pages;
    private final String subject;
    private boolean available = true;

    public Textbook(String title, String author, int pages, String subject) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public int getPages() {
        return pages;
    }

    @Override
    public String read() {
        return "Читаю учебник по предмету «" + subject + "»";
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
        return "Textbook{title='" + title + "', author='" + author + "', pages=" + pages
                + ", subject='" + subject + "'}";
    }
}
