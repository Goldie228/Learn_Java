# Конспект: Лабораторная работа №10

**Тема:** Разработка, отладка и испытание программ с использованием интерфейсов.

---

## 1. Что такое интерфейс
**Интерфейс** — контракт поведения: набор абстрактных методов без состояния
объекта. Неявно методы `public abstract`. Интерфейс нельзя создать через
`new`, но можно объявить переменную его типа.
```java
public interface Readable {
    String read();
    int getPages();
}
```

## 2. Реализация интерфейса
Класс указывает `implements` и обязан реализовать все абстрактные методы.
Реализующие методы должны быть `public` — сужать доступ нельзя.
```java
public class Novel implements Readable, Lendable {
    private String title;
    private int pages;
    @Override public String read() { return "Читаю роман «" + title + "»"; }
    @Override public int getPages() { return pages; }
    // lend(), returnBook(), isAvailable()
}
```

Если класс реализует не все методы, он объявляется `abstract`.
## 3. Несколько интерфейсов
```java
interface Drawable  { void draw(); }
interface Moveable  { void move(int dx, int dy); }
interface Resizable { void resize(double factor); }

class Shape implements Drawable, Moveable, Resizable {
    private int x, y;
    private double size;
    @Override public void draw() { System.out.println("Рисую фигуру"); }
    @Override public void move(int dx, int dy) { x += dx; y += dy; }
    @Override public void resize(double factor) { size *= factor; }
}
```

`Shape` доступен через любую ссылку: `Drawable d = shape;`.

## 4. default- и static-методы
- `default`-метод имеет реализацию и может переопределяться;
- `static`-метод вызывается через имя интерфейса: `Readable.formatPages(320)`.
```java
default int getReadingTimeMinutes() { return getPages() * 2; }
static String formatPages(int pages) { return pages + " стр."; }
```

## 5. Полиморфизм через интерфейс
Переменная интерфейсного типа ссылается на объект любого реализующего класса;
вызов метода связывается с фактическим типом во время выполнения.
```java
List<Readable> books = List.of(
        new Novel("1984", "Джордж Оруэлл", 320),
        new Textbook("Алгебра", "А. Г. Мерзляк", 288, "математика"));
for (Readable book : books) {
    System.out.println(book.read());
    System.out.println(book.getReadingTimeMinutes());
}
```
## 6. Интерфейс и абстрактный класс
| Признак | интерфейс | абстрактный класс |
|---|---|---|
| Наследование | несколько | только одно |
| Поля и конструктор | нет | есть |
| Назначение | контракт | общая база с состоянием |
## 7. Контрольные вопросы кратко
1. Интерфейс — контракт без состояния; класс — сущность с полями.
2. Методы: абстрактные, `default`, `static`, `private`; плюс константы.
3. Несколько интерфейсов — через запятую после `implements`.
4. `default` даёт готовое поведение, `static` — утилитарный метод.
5. Полиморфизм — вызов через интерфейсную ссылку идёт по фактическому классу.
