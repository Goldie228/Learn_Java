# Конспект: Лабораторная работа №14

**Тема:** Разработка, отладка и испытание программ с использованием файлового ввода и вывода.

---

## 1. Потоки ввода-вывода

**Поток (stream)** — абстракция источника или приёмника данных: файл,
консоль или сеть. Базовые классы: байтовые `InputStream`/`OutputStream`,
символьные `Reader`/`Writer`; методы: `read()`, `write()`, `flush()`, `close()`.

## 2. File, Path и Files

`File` описывает путь и свойства файла: `exists()`, `length()`, `getName()`,
`getParent()`, `isDirectory()`, `isFile()`, `canRead()`, `canWrite()`.
Пакет `java.nio.file` удобнее:

```java
Path path = Paths.get("data", "task1", "university.txt");
Files.createDirectories(path.getParent());
Files.writeString(path, "текст", StandardCharsets.UTF_8);
```

`Path` — неизменяемый путь, `Files` — статические операции: `exists`,
`size`, `getLastModifiedTime`, `readAllLines`, `newBufferedReader`.

## 3. Символьные потоки и UTF-8

```java
try (BufferedWriter writer =
        Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
    writer.write("Колледж бизнеса и права");
    writer.newLine();
}
try (BufferedReader reader =
        Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
```

## 4. try-with-resources

```java
try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
     BufferedWriter writer = Files.newBufferedWriter(out, StandardCharsets.UTF_8)) {
    // ...
}
```

Ресурсы закрываются автоматически в обратном порядке, даже при исключении.

## 5. Числа в текстовом файле

Числа удобно хранить по одному в строке:

```java
for (Integer value : values) {
    writer.write(String.valueOf(value));
    writer.newLine();
}
for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
    if (!line.isBlank()) {
        values.add(Integer.parseInt(line.trim()));
    }
}
```
Для вещественных чисел используют `Double.parseDouble`.

## 6. Случайные числа и разбиение
```java
Random random = new Random();
for (int i = 0; i < 20; i++) {
    writer.write(String.valueOf(random.nextInt(201) - 100));
    writer.newLine();
}
```

Число кратно `d`, если `value % d == 0`; это верно и для нуля, и для
отрицательных чисел. Остальные значения пишутся во второй файл.

## 7. Обработка ошибок

Операции с файлами объявляют `throws IOException` (checked-исключение).
Вызывающий код обрабатывает его через `try-catch` или пробрасывает дальше.
Пустой список, нулевой делитель и неверный диапазон дают
`IllegalArgumentException`.
