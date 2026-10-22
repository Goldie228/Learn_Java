# Конспект: Лабораторная работа №9

**Тема:** Абстрактные классы и методы.

---

## 1. Абстрактный класс и абстрактный метод

Абстрактный класс (`abstract`) описывает общий контракт наследников:
может содержать поля, конструкторы, конкретные и абстрактные методы.
Абстрактный метод не имеет тела — только сигнатуру и `;`.

```java
abstract class Transport {
    private double speed;
    public Transport(double speed) {
        if (speed <= 0) throw new IllegalArgumentException("Скорость > 0");
        this.speed = speed;
    }
    public abstract String deliver();
    public abstract double calculateTime(double d);
    public String displayInfo() { return "Скорость: " + speed; }
}
```

Класс обязан быть абстрактным, если содержит хотя бы один абстрактный
метод. Он может и не иметь таких методов — тогда просто запрещает
создание своих объектов.

## 2. Нельзя создать объект абстрактного класса

```java
// Transport t = new Transport(80);  // Transport is abstract
Transport t = new Truck(20, 80, 300, 15);
```

Ссылка абстрактного типа допустима и лежит в основе полиморфизма.

## 3. Наследники и полиморфизм

Конкретный наследник **обязан** реализовать все абстрактные методы. Если
хотя бы один не реализован, наследник тоже объявляется `abstract`.

```java
class Truck extends Transport {
    private double cargoWeight;
    public Truck(double speed, double weight) {
        super(speed);
        this.cargoWeight = weight;
    }
    @Override public String deliver() {
        return "Доставляет груз по суше";
    }
    @Override public double calculateTime(double distance) {
        if (distance < 0) throw new IllegalArgumentException("Расстояние < 0");
        return distance / getSpeed();
    }
}
```

Конструктор абстрактного класса вызывается через `super(...)` первой
строкой; сначала инициализируется база, затем поля наследника.

```java
Transport[] transports = { new Truck(80, 15), new Ship(80, 300) };
for (Transport t : transports) {
    System.out.println(t.deliver());
    System.out.println(t.calculateTime(500));
}
```

JVM вызывает реализацию фактического класса. У `Ship` формула
`distance / (speed * 0.8)`, поэтому время больше, чем у грузовика.

## 4. Валидация и пример Animal / Dog

`IllegalArgumentException` — непроверяемое исключение: `distance < 0`
или `speed <= 0`. У `Animal`/`Dog` сообщение "Конструктор Animal вызван"
появляется раньше "Конструктор Dog вызван", так как `super()` — первая
строка конструктора наследника.
