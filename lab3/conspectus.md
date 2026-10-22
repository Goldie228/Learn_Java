# Конспект: Лабораторная работа №3

**Тема:** Разработка, отладка и испытание программ с использованием строк

---

## 1. Классы для работы со строками

| Класс | Описание | Изменяемость |
|-------|----------|-------------|
| `String` | Неизменяемая строка | Immutable |
| `StringBuilder` | Изменяемая строка (не потокобезопасный) | Mutable |
| `StringBuffer` | Изменяемая строка (потокобезопасный) | Mutable |

---

## 2. Создание строк

### Способ 1: литерал
```java
String str1 = "Привет, мир!";
```

### Способ 2: оператор new
```java
String str2 = new String("Привет, мир!");
```

### Способ 3: из массива символов
```java
char[] chars = {'J', 'a', 'v', 'a'};
String str3 = new String(chars);
```

### Способ 4: пустая строка
```java
String empty = "";
String alsoEmpty = new String();
```

**Важно:** литералы строк хранятся в пуле строк (String Pool). Одинаковые литералы указывают на один объект.

---

## 3. Конкатенация строк

### Оператор +
```java
String a = "Мама ";
String b = "мыла ";
String c = "раму";
String result = a + b + c;  // "Мама мыла раму"
```

### Метод concat()
```java
String result = a.concat(b).concat(c);  // то же самое
```

### Конкатенация в цикле
```java
String[] words = {"Java", "is", "cool"};
String sentence = "";
for (int i = 0; i < words.length; i++) {
    sentence += words[i];
    if (i < words.length - 1) sentence += " ";
}
// "Java is cool"
```

---

## 4. Основные методы класса String

### Длина строки
```java
String s = "Hello";
int len = s.length();  // 5
```

### Доступ к символам
```java
String s = "Hello";
char first = s.charAt(0);   // 'H'
char last = s.charAt(4);    // 'o'
```

### Преобразование регистра
```java
String s = "Hello World";
s.toUpperCase();   // "HELLO WORLD"
s.toLowerCase();   // "hello world"
```

### Обрезка пробелов
```java
String s = "  Привет  ";
s.trim();  // "Привет"
```

### Поиск подстроки
```java
String s = "Hello World";
s.contains("World");      // true
s.startsWith("Hello");   // true
s.endsWith("World");     // true
```

### Поиск индекса
```java
String s = "Hello World";
s.indexOf('o');          // 4 (первое вхождение)
s.lastIndexOf('o');      // 7 (последнее вхождение)
s.indexOf("World");     // 6
s.indexOf("Java");      // -1 (не найдено)
```

### Замена
```java
String s = "Hello World";
s.replace('o', '0');           // "Hell0 W0rld"
s.replace("World", "Java");   // "Hello Java"
s.replaceAll("[aeiou]", "*"); // замена по regex
```

### Подстрока
```java
String s = "Hello World";
s.substring(6);       // "World" (с индекса 6 до конца)
s.substring(0, 5);    // "Hello" (с 0 по 4 включительно)
```

### Разбиение строки
```java
String s = "one,two,three";
String[] parts = s.split(",");  // {"one", "two", "three"}
```

### Сравнение строк
```java
String a = "Hello";
String b = "Hello";
String c = "hello";

a.equals(b);            // true (учитывает регистр)
a.equalsIgnoreCase(c);  // true (без учёта регистра)
a.compareTo(b);         // 0 (равны)
a.compareTo(c);         // отрицательное (a < c)
```

### Строковое представление числа
```java
// Число → Строка
String numStr = String.valueOf(42);       // "42"
String numStr2 = Integer.toString(42);    // "42"

// Строка → Число
int num = Integer.parseInt("42");         // 42
double d = Double.parseDouble("3.14");    // 3.14
```

---

## 5. Массив строк

```java
// Создание
String[] names = {"Аня", "Борис", "Вика"};

// Обращение
String first = names[0];  // "Аня"

// Перебор
for (int i = 0; i < names.length; i++) {
    System.out.println(names[i]);
}

// For-each
for (String name : names) {
    System.out.println(name);
}
```

---

## 6. StringBuilder — изменяемая строка

```java
StringBuilder sb = new StringBuilder("Hello");

sb.append(" World");      // "Hello World"
sb.insert(5, ",");         // "Hello, World"
sb.delete(5, 6);           // "Hello World" (удаляем запятую)
sb.replace(6, 11, "Java"); // "Hello Java"
sb.reverse();              // "avaJ olleH"

String result = sb.toString();  // преобразование в String
```

### Когда использовать StringBuilder?
- Много операций конкатенации в цикле
- Частые вставки/удаления символов

---

## 7. StringBuffer — потокобезопасная версия

Интерфейс идентичен `StringBuilder`, но методы синхронизированы:
```java
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World");
```

Используется в многопоточных средах. В однопоточных приложениях предпочтительнее `StringBuilder`.

---

## 8. Форматирование строк (printf)

```java
String name = "Иван";
int age = 25;
double salary = 1500.50;

System.out.printf("Имя: %s, Возраст: %d, Зарплата: %.2f%n", name, age, salary);
```

### Основные форматы

| Формат | Описание |
|--------|----------|
| `%s` | строка |
| `%d` | целое число |
| `%f` | вещественное число |
| `%n` | перевод строки |
| `%5d` | число с выравниванием вправо (5 позиций) |
| `%-15s` | строка с выравниванием влево (15 позиций) |
| `%.2f` | число с 2 знаками после запятой |

---

## 9. Регулярные выражения

### Класс Pattern
```java
import java.util.regex.Pattern;

Pattern pattern = Pattern.compile("\\d+");  // шаблон: одна или более цифр
Matcher matcher = pattern.matcher("abc123def456");
```

### Класс Matcher
```java
import java.util.regex.Matcher;

Pattern p = Pattern.compile("\\d+");
Matcher m = p.matcher("abc123def456");

while (m.find()) {
    System.out.println(m.group());  // "123", "456"
}
```

### Методы Matcher
- `find()` — поиск следующего совпадения
- `matches()` — вся строка соответствует шаблону
- `group()` — найденная подстрока
- `start()` — индекс начала совпадения
- `end()` — индекс конца совпадения
- `replaceAll(String replacement)` — замена всех совпадений

### Методы Pattern
- `compile(String regex)` — компиляция шаблона
- `matches(String regex, CharSequence input)` — проверка строки
- `split(CharSequence input)` — разбиение строки

---

## 10. Предопределённые классы символов

| Символ | Описание | Эквивалент |
|--------|----------|------------|
| `.` | любой символ | — |
| `\d` | цифра | `[0-9]` |
| `\D` | не цифра | `[^0-9]` |
| `\s` | пробел/таб/перевод строки | `[ \t\n\x0B\f\r]` |
| `\S` | не пробел | `[^\s]` |
| `\w` | буква, цифра, _ | `[0-9_A-Za-z]` |
| `\W` | не буква/цифра/ _ | `[^\w]` |

---

## 11. Квантификаторы

| Квантификатор | Описание |
|---------------|----------|
| `X*` | 0 или более раз |
| `X+` | 1 или более раз |
| `X?` | 0 или 1 раз |
| `X{n}` | ровно n раз |
| `X{n,}` | n или более раз |
| `X{n,m}` | от n до m раз |

---

## 12. Логические операции в regex

| Оператор | Описание | Пример |
|----------|----------|--------|
| `X\|Y` | X или Y | `cat\|dog` |
| `(X)` | группировка | `(ab)+` |
| `[abc]` | один из символов | `[aeiou]` |
| `[^abc]` | любой символ кроме a, b, c | `[^0-9]` |
| `[a-z]` | диапазон символов | `[A-Za-z]` |

---

## 13. Примеры регулярных выражений

```java
// Найти все слова
Pattern.compile("\\b\\w+\\b");

// Найти все числа
Pattern.compile("\\d+");

// Найти email
Pattern.compile("[\\w.]+@[\\w.]+");

// Палиндром (проверка строки)
Pattern.compile("^(.)(.*)(.)$");  // упрощённо

// Замена всех пробелов на _
str.replaceAll("\\s+", "_");
```

---

## 14. Краткая шпаргалка

```java
// Создание
String s = "Hello";
StringBuilder sb = new StringBuilder("Hello");

// Длина
s.length();

// Символы
s.charAt(0);
s.toCharArray();          // String → char[]
new String(charArray);    // char[] → String

// Поиск
s.contains("el");
s.indexOf("el");
s.startsWith("He");
s.endsWith("lo");

// Модификация
s.replace("l", "L");
s.substring(1, 3);
s.trim();
s.toUpperCase();
s.toLowerCase();
s.split(" ");

// Сравнение
s.equals("Hello");
s.equalsIgnoreCase("hello");

// Форматирование
System.out.printf("Name: %s, Age: %d%n", name, age);

// Regex
Pattern p = Pattern.compile("\\d+");
Matcher m = p.matcher(s);
while (m.find()) { m.group(); }
```
