# Конспект: Лабораторная работа №2

**Тема:** Разработка, отладка и испытание программ с использованием массивов

---

## 1. Что такое массив

Массив в Java — это **объект**, хранящий фиксированное количество элементов одного типа. Состоит из:
- **Поля `length`** — размер массива (количество элементов)
- **Собственно данных** — элементы с индексами от `0` до `length - 1`

---

## 2. Объявление и создание

### Способ 1: объявление + создание
```java
int[] nums;                // объявление ссылки
nums = new int[5];         // создание массива из 5 элементов (все = 0)
```

### Способ 2: сразу при объявлении
```java
int[] nums = new int[5];   // массив из 5 нулей
```

### Способ 3: инициализация списком значений
```java
int[] nums = {1, 2, 3, 4, 5};          // размер = 5
int[] nums2 = new int[] {10, 20, 30};  // то же самое
```

**Важно:** при инициализации списком размер **не указывается** в скобках — он вычисляется автоматически.

### Два варианта записи объявления
```java
int nums[];    // C-стиль
int[] nums;    // Java-стиль (предпочтительнее)
```

---

## 3. Значения по умолчанию

| Тип         | Значение по умолчанию |
|-------------|----------------------|
| `byte`      | `0`                  |
| `short`     | `0`                  |
| `int`       | `0`                  |
| `long`      | `0L`                 |
| `float`     | `0.0f`               |
| `double`    | `0.0`                |
| `boolean`   | `false`              |
| `char`      | `'\u0000'`           |
| Ссылочные   | `null`               |

---

## 4. Обращение к элементам

Индексация начинается с **0**:

```java
int[] nums = {10, 20, 30, 40, 50};

nums[0]   // 10 (первый элемент)
nums[2]   // 30 (третий элемент)
nums[4]   // 50 (последний элемент)
```

**Выход за границы** массива вызывает `ArrayIndexOutOfBoundsException`:
```java
int[] nums = new int[3];
nums[5] = 100;  // ОШИБКА!
```

---

## 5. Длина массива

Свойство `length` (не метод!):
```java
int[] nums = {1, 2, 3, 4, 5};
int len = nums.length;     // 5

// Последний элемент:
int last = nums[nums.length - 1];  // 5
```

---

## 6. Перебор элементов

### Цикл `for`
```java
int[] nums = {10, 20, 30};

for (int i = 0; i < nums.length; i++) {
    System.out.println(nums[i]);
}
```

### Цикл `for-each`
```java
int[] nums = {10, 20, 30};

for (int n : nums) {
    System.out.println(n);
}
```

---

## 7. Заполнение массива

### Вручную
```java
int[] nums = new int[5];
nums[0] = 10;
nums[1] = 20;
// ...
```

### Через цикл (с клавиатуры)
```java
Scanner sc = new Scanner(System.in);
int[] nums = new int[5];

for (int i = 0; i < nums.length; i++) {
    nums[i] = sc.nextInt();
}
```

### Случайными числами
```java
import java.util.Random;

Random rand = new Random();
int[] nums = new int[5];

for (int i = 0; i < nums.length; i++) {
    nums[i] = rand.nextInt(100); // от 0 до 99
}
```

### Через `Arrays.fill()`
```java
import java.util.Arrays;

int[] nums = new int[5];
Arrays.fill(nums, 42);  // все элементы = 42
```

---

## 8. Копирование массива

### Вручную через цикл
```java
int[] src = {1, 2, 3};
int[] dst = new int[src.length];

for (int i = 0; i < src.length; i++) {
    dst[i] = src[i];
}
```

### Через `Arrays.copyOf()`
```java
import java.util.Arrays;

int[] src = {1, 2, 3};
int[] dst = Arrays.copyOf(src, src.length);
```

### Через `System.arraycopy()`
```java
int[] src = {1, 2, 3};
int[] dst = new int[3];
System.arraycopy(src, 0, dst, 0, src.length);
```

---

## 9. Двумерные массивы (матрицы)

Двумерный массив — это **массив массивов**.

### Создание
```java
// Способ 1: явное создание
int[][] matrix = new int[3][4];  // 3 строки, 4 столбца

// Способ 2: список инициализации
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Способ 3: неправильный массив (jagged)
int[][] matrix = new int[3][];
matrix[0] = new int[]{1, 2};
matrix[1] = new int[]{3, 4, 5};
matrix[2] = new int[]{6};
```

### Доступ к элементам
```java
matrix[1][2]  // элемент во 2-й строке, 3-м столбце
```

### Перебор двумерного массива
```java
for (int i = 0; i < matrix.length; i++) {          // строки
    for (int j = 0; j < matrix[i].length; j++) {   // столбцы
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```

---

## 10. Диагонали матрицы

Для квадратной матрицы `N x N`:

### Главная диагональ
Элементы `[i][i]`:
```
[0][0]  [0][1]  [0][2]
[1][0]  [1][1]  [1][2]
[2][0]  [2][1]  [2][2]
```
Главная диагональ: `[0][0]`, `[1][1]`, `[2][2]`

```java
for (int i = 0; i < n; i++) {
    sum += matrix[i][i];
}
```

### Побочная диагональ
Элементы `[i][n - 1 - i]`:
```
[0][2], [1][1], [2][0]
```

```java
for (int i = 0; i < n; i++) {
    sum += matrix[i][n - 1 - i];
}
```

---

## 11. Полезные методы из `java.util.Arrays`

```java
import java.util.Arrays;

Arrays.sort(nums);              // сортировка
Arrays.toString(nums);          // строковое представление
Arrays.copyOf(src, length);     // копирование
Arrays.fill(nums, value);       // заполнение
Arrays.equals(a, b);            // сравнение массивов
Arrays.binarySearch(nums, key); // бинарный поиск (нужна сортировка!)
```

---

## 12. Ошибки при работе с массивами

| Ошибка | Причина |
|--------|---------|
| `ArrayIndexOutOfBoundsException` | Обращение за пределы массива |
| `NullPointerException` | Массив не создан (`int[] a; a[0] = 1;`) |
| `NegativeArraySizeException` | Отрицательный размер (`new int[-5]`) |

---

## 13. Краткая шпаргалка

```java
// Объявление и инициализация
int[] arr1 = new int[10];          // 10 нулей
int[] arr2 = {1, 2, 3};           // инициализация
int[][] mat = new int[3][4];       // матрица 3x4

// Длина
arr1.length                        // 10
mat.length                         // 3 (строки)
mat[0].length                      // 4 (столбцы)

// Перебор
for (int i = 0; i < arr1.length; i++) { ... }
for (int x : arr1) { ... }
for (int i = 0; i < mat.length; i++)
    for (int j = 0; j < mat[i].length; j++) { ... }
```
