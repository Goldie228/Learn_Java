---
tags:
  - java
  - generics
  - collections
  - lab12
---

# Generics и коллекции (ЛР12)

> [!abstract] Одной фразой
> Generics дают типобезопасность без приведения типов, а коллекции —
> готовые структуры данных: список, множество, очередь, словарь.

## Generics

### Обобщённый класс

```java
public class Box<T> {
    private T value;

    public void setValue(T value) { this.value = value; }
    public T getValue() { return value; }
}

Box<String> stringBox = new Box<>();
stringBox.setValue("Hello");
String s = stringBox.getValue();       // без (String)

Box<Integer> intBox = new Box<>();
intBox.setValue(123);
int n = intBox.getValue();
```

### Два параметра

```java
public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() { return key; }
    public V getValue() { return value; }
}

Pair<String, Integer> age = new Pair<>("Age", 25);
```

### Соглашения об именах

| Буква | Смысл |
|-------|-------|
| `T` | Type |
| `E` | Element |
| `K` | Key |
| `V` | Value |
| `N` | Number |
| `S`, `U` | второй, третий тип |

### Ограничения и wildcards

```java
// T должен быть числом или его наследником
public class Stats<T extends Number> {
    private T value;
    public double asDouble() { return value.doubleValue(); }
}

// читаем числа любого подтипа
void printAll(List<? extends Number> list) {
    for (Number n : list) System.out.println(n);
}

// можем добавлять Integer и его подтипы
void addInts(List<? super Integer> list) {
    list.add(1);
}
```

`? extends` — «читаем», `? super` — «пишем» (PECS: Producer Extends,
Consumer Super).

### Что важно помнить

- В generic нельзя примитивы: только `Integer`, `Double`, `Boolean`.
- Нельзя `new T()`, `new T[]`, `T.class`.
- Нельзя перегрузить метод только по generic-типу (стирание типов).
- Diamond `new ArrayList<>()` — тип выводится.
- Generics проверяются компилятором, в runtime информация стирается.

## Иерархия коллекций

```text
Iterable<E>
└── Collection<E>
    ├── List<E>      — упорядоченный, дубликаты, индекс
    ├── Set<E>       — уникальные элементы
    └── Queue<E>     — очередь (FIFO)
Map<K,V>             — ключ → значение (отдельная иерархия)
```

| Интерфейс | Порядок | Дубликаты | Доступ |
|-----------|---------|-----------|--------|
| `List` | да | да | по индексу |
| `Set` | зависит от реализации | нет | только перебор |
| `Queue` | FIFO | да | с головы/хвоста |
| `Map` | зависит | ключи уникальны | по ключу |

### Реализации

| Интерфейс | Классы | Особенность |
|-----------|--------|-------------|
| `List` | `ArrayList` | динамический массив, быстрый `get(i)` |
| `List` | `LinkedList` | двусвязный список, быстрое добавление/удаление |
| `Set` | `HashSet` | хеш-таблица, порядок не гарантирован |
| `Set` | `LinkedHashSet` | порядок вставки |
| `Set` | `TreeSet` | отсортированное дерево |
| `Map` | `HashMap` | хеш-таблица, порядок не гарантирован |
| `Map` | `LinkedHashMap` | порядок вставки |
| `Map` | `TreeMap` | ключи отсортированы |
| `Queue` | `ArrayDeque` | быстрая очередь/стек |

> [!important] Что выбрать
> Нужен индекс — `ArrayList`. Нужны уникальные — `HashSet`. Нужен порядок
> вставки — `LinkedHashSet`/`LinkedHashMap`. Нужна сортировка — `TreeSet`/
> `TreeMap`. Нужен ключ → значение — `HashMap`.

## Основные операции

```java
List<String> list = new ArrayList<>();
list.add("Java");
list.add("SQL");
list.add(1, "Git");          // вставка по индексу
list.set(0, "Java 21");      // замена
list.remove("SQL");
list.remove(0);              // по индексу
list.contains("Git");
list.size();
list.isEmpty();
list.clear();
```

```java
Set<String> set = new HashSet<>();
set.add("a");
set.add("a");        // false, дубликат
set.contains("a");
```

```java
Map<String, Integer> map = new HashMap<>();
map.put("age", 20);
map.put("age", 21);              // перезапись
map.get("age");                  // 21
map.getOrDefault("x", 0);        // 0
map.containsKey("age");
map.remove("age");
map.size();
```

### Перебор

```java
for (String s : list) { ... }

for (Map.Entry<String, Integer> e : map.entrySet()) {
    System.out.println(e.getKey() + " = " + e.getValue());
}

map.forEach((k, v) -> System.out.println(k + " = " + v));

for (String key : map.keySet()) { ... }
for (Integer value : map.values()) { ... }
```

### Удаление во время перебора

```java
Iterator<Hotel> it = hotels.iterator();
while (it.hasNext()) {
    if (it.next().getAverageRating() < 3) {
        it.remove();          // правильно
    }
}
```

> [!warning] `ConcurrentModificationException`
> Нельзя удалять из коллекции через `list.remove(...)` внутри for-each.
> Используй `Iterator.remove()` или `removeIf(...)`.

```java
hotels.removeIf(h -> h.getAverageRating() < 3);
```

## Сортировка

```java
List<Integer> numbers = new ArrayList<>(List.of(3, 1, 2));
Collections.sort(numbers);          // по возрастанию
numbers.sort(Comparator.reverseOrder());

List<Hotel> sorted = new ArrayList<>(hotels);
sorted.sort(
    Comparator.comparingDouble(Hotel::getAverageRating)
              .reversed()
              .thenComparing(Hotel::getName, String.CASE_INSENSITIVE_ORDER)
);
```

- `Comparable` — «я умею сравнивать себя» (`compareTo`).
- `Comparator` — «внешнее правило сравнения» (`compare`).
- `Comparator.comparing(...)` — по ключу.
- `.reversed()` — обратный порядок.
- `.thenComparing(...)` — второй критерий при равенстве.

## `equals` и `hashCode`

Если объекты кладутся в `HashSet` или используются как ключи `HashMap`:

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tool tool = (Tool) o;
    return id == tool.id && Objects.equals(name, tool.name);
}

@Override
public int hashCode() {
    return Objects.hash(id, name);
}
```

Правило: переопределил `equals` — переопредели `hashCode`.

## Код варианта 9 (ЛР12)

### Задание 1 — `Map<String, Tool>`

```java
public class Tool {
    private String name;
    private int id;
    private double weight;

    public Tool(String name, int id, double weight) {
        this.name = name;
        this.id = id;
        this.weight = weight;
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public double getWeight() { return weight; }

    public String displayInfo() {
        return name + " (id=" + id + ", вес=" + weight + " кг)";
    }
}

class Hammer extends Tool {
    private final double handleLength;
    Hammer(String name, int id, double weight, double handleLength) {
        super(name, id, weight);
        this.handleLength = handleLength;
    }
    @Override public String displayInfo() {
        return super.displayInfo() + ", длина рукоятки: " + handleLength + " см";
    }
}

class Screwdriver extends Tool {
    private final String tipType;
    Screwdriver(String name, int id, double weight, String tipType) {
        super(name, id, weight);
        this.tipType = tipType;
    }
    @Override public String displayInfo() {
        return super.displayInfo() + ", тип жала: " + tipType;
    }
}

class Wrench extends Tool {
    private final int size;
    Wrench(String name, int id, double weight, int size) {
        super(name, id, weight);
        this.size = size;
    }
    @Override public String displayInfo() {
        return super.displayInfo() + ", размер: " + size + " мм";
    }
}
```

```java
Map<String, Tool> tools = new LinkedHashMap<>();
tools.put("Молоток слесарный", new Hammer("Молоток слесарный", 101, 1.2, 30));
tools.put("Молоток отделочный", new Hammer("Молоток отделочный", 102, 0.85, 28));
tools.put("Молоток-гвоздодёр", new Hammer("Молоток-гвоздодёр", 103, 1.45, 32));
tools.put("Отвёртка крестовая", new Screwdriver("Отвёртка крестовая", 201, 0.15, "PH2"));
tools.put("Отвёртка плоская", new Screwdriver("Отвёртка плоская", 202, 0.12, "SL3"));
tools.put("Отвёртка прецизионная", new Screwdriver("Отвёртка прецизионная", 203, 0.05, "PH0"));
tools.put("Ключ рожковый", new Wrench("Ключ рожковый", 301, 0.35, 17));
tools.put("Ключ комбинированный", new Wrench("Ключ комбинированный", 302, 0.42, 19));

for (Map.Entry<String, Tool> e : tools.entrySet()) {
    System.out.println(e.getKey() + " → " + e.getValue().displayInfo());
}
```

### Задание 2 — отели

```java
public class Hotel {
    private final int id;
    private final String name;
    private final String city;
    private int stars;
    private final List<Integer> guestRatings;

    public Hotel(int id, String name, String city, int stars, List<Integer> ratings) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stars = stars;
        this.guestRatings = new ArrayList<>(ratings);   // защитная копия
        setStars(stars);
    }

    public double getAverageRating() {
        if (guestRatings.isEmpty()) return 0.0;
        double sum = 0;
        for (int r : guestRatings) sum += r;
        return sum / guestRatings.size();
    }

    public int getStars() { return stars; }
    public void setStars(int stars) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Звёзды 1..5");
        }
        this.stars = stars;
    }
    public void addRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Оценка 1..5");
        }
        guestRatings.add(rating);
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public List<Integer> getGuestRatings() {
        return Collections.unmodifiableList(guestRatings);
    }
}
```

```java
public class HotelService {
    public List<Hotel> lowRated(List<Hotel> hotels) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel h : hotels) {
            if (h.getAverageRating() < 3.0) result.add(h);
        }
        return result;
    }

    public void raiseStarsForBest(List<Hotel> hotels) {
        for (Hotel h : hotels) {
            if (h.getAverageRating() >= 4.0) {
                h.setStars(Math.min(5, h.getStars() + 1));
            }
        }
    }

    public List<Hotel> byStars(List<Hotel> hotels, int stars) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel h : hotels) {
            if (h.getStars() == stars) result.add(h);
        }
        return result;
    }

    public List<Hotel> sortedByRating(List<Hotel> hotels) {
        List<Hotel> result = new ArrayList<>(hotels);
        result.sort(
            Comparator.comparingDouble(Hotel::getAverageRating)
                      .reversed()
                      .thenComparing(Hotel::getName, String.CASE_INSENSITIVE_ORDER)
        );
        return result;
    }
}
```

## Что спросят на защите

1. **Основные интерфейсы коллекций?** `Collection`, `List`, `Set`, `Queue`, `Map`.
2. **`List` vs `Set`?** Порядок и дубликаты: список — да/да, множество — нет дубликатов.
3. **`ArrayList` vs `LinkedList`?** Массив с быстрым доступом vs список с быстрыми вставками.
4. **`HashSet` vs `TreeSet`?** Хеш без порядка vs отсортированное дерево.
5. **`HashMap` vs `TreeMap`?** Хеш без порядка vs сортировка по ключам.
6. **Как перебрать?** for-each, `Iterator`, `forEach`, `entrySet`.
7. **Что такое `Iterator`?** Курсор с `hasNext()`/`next()`/`remove()`.
8. **Как отсортировать?** `Collections.sort`, `list.sort`, `Comparator`, `Comparable`.

> [!tip] Фраза для ответа
> «Generics обеспечивают проверку типов на компиляции. Коллекцию выбираю по
> задаче: `ArrayList` для доступа по индексу, `HashSet` для уникальности,
> `HashMap` для пар ключ-значение, `TreeMap`/`TreeSet` для сортировки».

Дальше: [[06 Исключения]] → [[09 Код под диктовку]]
