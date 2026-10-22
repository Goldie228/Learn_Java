# Конспект: Лабораторная работа №4

**Тема:** Разработка, отладка и испытание программ с использованием системы сборки проекта Maven

---

## 1. Зачем нужны системы сборки

Система сборки автоматизирует типовые операции над проектом:

- компиляция исходного кода;
- запуск тестов;
- упаковка в архив (`jar`, `war`);
- управление сторонними библиотеками (зависимостями);
- публикация готового артефакта.

Ручная компиляция (`javac`, `java`) годится для одного-двух файлов, но не для реального проекта с десятками классов и библиотек.

---

## 2. Gradle

- Система автоматизации сборки с открытым исходным кодом.
- Использует **DSL на языке Groovy** (или Kotlin) вместо XML.
- Порядок выполнения задач задаётся **направленным ациклическим графом (DAG)**.
- Появилась в 2007 году; в 2013 принята Google как штатная система сборки Android.
- Поддерживает **инкрементную сборку**: пересобираются только изменённые части.
- Умеет работать с Java, Scala, Groovy, Kotlin и др.

---

## 3. Maven

- Инструмент автоматизации сборки проектов на Java (Apache).
- Конфигурация проекта описывается **XML-файлом `pom.xml`** (Project Object Model).
- Имеет **фиксированную линейную модель фаз** жизненного цикла.
- Загружает библиотеки и плагины из репозиториев в локальный кэш `~/.m2/repository`.
- Использует **стандартную структуру каталогов** и формат идентификации артефактов **GAV** (groupId : artifactId : version).
- Кроме Java поддерживает Scala, Ruby, C# и др.

---

## 4. Maven в сравнении с Gradle

| Критерий | Maven | Gradle |
|----------|-------|--------|
| Конфигурация | XML (`pom.xml`) | Groovy/Kotlin DSL |
| Модель | Линейные фазы | Граф задач (DAG) |
| Инкрементность | Ограниченная | Полная |
| Гибкость | Ниже | Выше |
| Плагины | Больше | Меньше, но растёт |
| Структура каталогов | Стандартная | Как в Maven, но настраиваемая |
| Порог входа | Ниже | Выше |

**Вывод:** Maven проще и предсказуемее за счёт стандартов; Gradle гибче и быстрее на больших проектах.

---

## 5. Жизненный цикл сборки Maven

Фазы выполняются строго по порядку; вызов фазы выполняет все предыдущие.

| Команда | Что делает |
|---------|------------|
| `mvn clean` | удаляет каталог `target` (скомпилированные файлы) |
| `mvn validate` | проверяет, вся ли информация доступна для сборки |
| `mvn compile` | компилирует исходный код |
| `mvn test` | запускает тесты |
| `mvn package` | упаковывает проект в `jar`/`war` |
| `mvn verify` | выполняет проверки готового пакета |
| `mvn install` | помещает пакет в локальный репозиторий `~/.m2` |
| `mvn site` | создаёт документацию проекта |
| `mvn deploy` | копирует архив в удалённый репозиторий |

Комбинирование: `mvn clean package`, `mvn clean install`.

---

## 6. Структура Maven-проекта

```
lab4/
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── by/epam/learn/App.java
    └── test
        └── java
            └── by/epam/learn/AppTest.java
```

- `src/main/java` — исходный код приложения;
- `src/test/java` — модульные тесты;
- `target` — результат сборки (создаётся автоматически).

---

## 7. Файл pom.xml

Основные элементы:

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>by.epam.learn</groupId>       <!-- организация -->
    <artifactId>lab4</artifactId>          <!-- имя проекта -->
    <version>1.0-SNAPSHOT</version>        <!-- версия -->
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.release>21</maven.compiler.release>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

**Координаты GAV** однозначно идентифицируют артефакт в любом репозитории.

**Scope** зависимости:
- `compile` (по умолчанию) — нужна для компиляции и запуска;
- `test` — только для тестов;
- `provided` — предоставляется средой (например, сервлет-контейнером);
- `runtime` — нужна только во время выполнения.

---

## 8. Генерация проекта

Через архетип Maven (аналог шаблона):

```bash
mvn archetype:generate \
    -DgroupId=by.epam.learn \
    -DartifactId=lab4 \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DarchetypeVersion=1.4 \
    -DinteractiveMode=false
```

Архетип `maven-archetype-quickstart` создаёт `App.java` с методом `main`
и `AppTest.java` с примером теста.

В IntelliJ IDEA: `File → New → Project → Generators → Maven Archetype`,
выбрать `org.apache.maven.archetypes:maven-archetype-quickstart`.

---

## 9. Запуск приложения

```bash
# Компиляция и упаковка
mvn clean package

# Запуск через classpath
java -cp target/classes by.epam.learn.App
```

---

## 10. Краткая шпаргалка

```bash
mvn -version          # версия Maven и JDK
mvn clean             # очистить target
mvn compile           # скомпилировать
mvn test              # прогнать тесты
mvn package           # собрать jar
mvn install           # установить в ~/.m2
mvn dependency:tree   # дерево зависимостей
```
