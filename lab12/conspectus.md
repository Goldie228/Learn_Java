# Конспект: Лабораторная работа №12

**Тема:** Обобщённые типы и коллекции значений.

---

## 1. Обобщённые типы (generics)

Обобщённый класс хранит значение типа-параметра, который задаётся при
создании объекта; компилятор проверяет тип и избавляет от приведения:

```java
class Box<T> {
    private T value;
    public void setValue(T value) { this.value = value; }
    public T getValue() { return value; }
}
Box<String> box = new Box<>();
box.setValue("Hello");
String s = box.getValue();       // без приведения типов
```

Имена параметров: `T` — Type, `E` — Element, `K` — Key, `V` — Value, `N` — Number.
Параметров может быть несколько: `class Pair<K, V> { private K key; private V value; ... }`,
`Pair<String, Integer> p = new Pair<>("Возраст", 25);`.

Примитив параметром быть не может (`Box<int>` — ошибка, нужен `Box<Integer>`).
После компиляции типы стираются (type erasure): `new T()` и `T[]` создать нельзя.

## 2. Коллекции

`Collection<E>` — корень иерархии; `List<E>` — упорядоченный список с
дубликатами и доступом по индексу; `Set<E>` — уникальные элементы без
гарантированного порядка; `Map<K,V>` — «ключ — значение», ключи уникальны.

`ArrayList` — быстрый доступ по индексу, `LinkedList` — быстрые вставки;
`HashSet` — быстрый неупорядоченный поиск, `TreeSet` — сортировка;
`HashMap` — доступ O(1), `TreeMap` — сортированные ключи, `LinkedHashMap` —
порядок вставки.

## 3. List и Map на практике

```java
List<Hotel> hotels = new ArrayList<>();
hotels.add(new Hotel(...));
for (Hotel h : hotels) System.out.println(h.displayInfo());
Map<String, Tool> tools = new LinkedHashMap<>();
tools.put("Молоток", new Hammer(...));
tools.put("Молоток", new Screwdriver(...)); // ключ тот же, значение новое
System.out.println(tools.size());           // 1
tools.forEach((k, v) -> System.out.println(k + " → " + v));
```

## 4. Iterator и Comparator

`Iterator` (`hasNext()`, `next()`, `remove()`) позволяет удалять элементы при
обходе; `for-each` этого не разрешает:

```java
Iterator<Hotel> it = hotels.iterator();
while (it.hasNext()) { if (it.next().getAverageRating() < 3) it.remove(); }
```

`Comparator` задаёт внешний порядок; цепочка `.reversed()` и `.thenComparing`
даёт сортировку по убыванию рейтинга с алфавитной ничьей:

```java
list.sort(Comparator.comparingDouble(Hotel::getAverageRating).reversed()
        .thenComparing(Hotel::getName, String.CASE_INSENSITIVE_ORDER));
```

## 5. Шпаргалка

| Коллекция | Порядок | Дубликаты | Доступ |
|---|---|---|---|
| `ArrayList` | вставки | да | индекс, O(1) |
| `HashSet` | нет | нет | значение, O(1) |
| `TreeSet` | сортировка | нет | значение, O(log n) |
| `HashMap` | нет | ключи уникальны | ключ, O(1) |
| `TreeMap` | по ключам | ключи уникальны | ключ, O(log n) |

## Контрольные вопросы

1. **List и Set:** `List` упорядочен и допускает дубликаты, `Set` хранит только уникальные элементы.
2. **ArrayList и LinkedList:** массив с доступом O(1) против списка с быстрыми вставками и доступом O(n).
3. **HashSet и TreeSet:** быстрый неупорядоченный набор против отсортированного дерева.
4. **HashMap и TreeMap:** доступ O(1) без порядка против O(log n) с сортировкой ключей.
5. **Перебор и сортировка:** `for-each`, `Iterator`, `entrySet()`; `list.sort(comparator)`.
