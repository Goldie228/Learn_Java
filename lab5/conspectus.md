# Конспект: Лабораторная работа №5

**Тема:** Разработка, отладка и испытание программ с использованием классов и созданием объектов

---

## 1. Структура класса в Java

```java
public class Cat {
    private int weight;    // атрибут (поле)
    private String name;

    public void eat() {    // метод
        System.out.print("Eating...\n");
    }
}
```

- `public` — модификатор доступа к классу (доступен другим классам);
- `class` — ключевое слово, объявляющее класс;
- `Cat` — имя класса (пишется с заглавной буквы);
- `{ }` — тело класса, внутри размещаются поля и методы.

### Атрибуты (поля)

Переменные, описывающие состояние объекта:

```java
private int weight;      // вес
private String name;     // имя
private String color;    // окрас
```

`private` — доступ только из этого же класса. `int`, `String` — типы данных.
После каждого объявления ставится `;`.

### Модификаторы доступа

| Модификатор | Доступ |
|-------------|--------|
| `private` | только внутри класса |
| `package-private` (без слова) | внутри пакета |
| `protected` | внутри пакета и в наследниках |
| `public` | отовсюду |

---

## 2. Методы класса

```java
public void eat() { ... }                 // ничего не возвращает
public String speak(String words) {       // возвращает String, принимает параметр
    String phrase = words + "...mauu...\n";
    return phrase;
}
```

Разбор сигнатуры `public String speak(String words)`:

- `public` — метод доступен другим классам;
- `String` — тип возвращаемого значения (`void` — ничего не возвращает);
- `speak` — имя метода (с маленькой буквы);
- `(String words)` — входные параметры;
- `{ }` — тело метода;
- `return` — возвращает значение из метода.

---

## 3. Создание экземпляра класса

```java
Cat ourcat = new Cat();
```

- `new Cat()` — в памяти создаётся объект класса `Cat`;
- `ourcat` — переменная типа `Cat`, ссылающаяся на этот объект.

Вызов методов через объект:

```java
ourcat.eat();
ourcat.sleep();
String say = ourcat.speak("Play with me");
System.out.println(say);   // Play with me...mauu...
```

---

## 4. Конструкторы

Конструктор — метод, который вызывается при создании объекта через `new`.
Имя конструктора совпадает с именем класса, типа возврата нет.

```java
public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}
```

`this` — ссылка на текущий объект; позволяет отличать поля от параметров
с одинаковыми именами. Если конструктор не объявлен, компилятор создаёт
конструктор по умолчанию без параметров.

---

## 5. Массив объектов

```java
Book[] books = {
    new Book("Война и мир", "Л. Н. Толстой", 1869),
    new Book("Евгений Онегин", "А. С. Пушкин", 1833)
};

for (Book b : books) {
    System.out.println(b);
}
```

Массив хранит **ссылки** на объекты. Поиск максимума/минимума — обычный
перебор с сравнением поля:

```java
Book oldest = books[0];
for (Book b : books) {
    if (b.getYear() < oldest.getYear()) {
        oldest = b;
    }
}
```

---

## 6. Перечисления (enum)

До Java 5 ограниченные наборы значений задавали константами `static final int`,
что небезопасно и нечитаемо. С Java 5 используется `enum`:

```java
public enum Gender {
    MALE, FEMALE
}
```

Перечисление — это класс, наследник `java.lang.Enum`. Объекты создаются
прямым объявлением, **без** оператора `new`.

### Методы элементов enum

| Метод | Описание |
|-------|----------|
| `values()` | массив всех элементов в порядке объявления |
| `valueOf(String)` | элемент по имени строки |
| `ordinal()` | позиция элемента (с нуля) |
| `name()` | имя элемента (как `toString()`) |
| `compareTo(T)` | сравнение элементов |

### Поля, конструкторы и методы в enum

```java
public enum Gender {
    MALE("мужской"),
    FEMALE("женский");

    private final String ru;

    Gender(String ru) {      // конструктор не может быть public/protected
        this.ru = ru;
    }

    @Override
    public String toString() {
        return ru;
    }
}
```

Конструктор вызывается автоматически при инициализации любого элемента.

### Ограничения enum

Запрещено: быть суперклассом, быть подклассом, быть абстрактным,
быть параметризованным, создавать экземпляры через `new`.

---

## 7. Краткая шпаргалка

```java
// Объявление класса
public class Student {
    private String name;      // поля
    private int age;

    public Student(String name, int age) {   // конструктор
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; } // геттер

    public void displayInfo() {              // метод
        System.out.println(name + ", " + age);
    }
}

// Создание объекта
Student s = new Student("Иван", 20);
s.displayInfo();

// Перечисление
enum Role { ADMIN, USER, GUEST }
Role r = Role.ADMIN;
```
