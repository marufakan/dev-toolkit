# 📘 Java Record Kullanım Rehberi

## 📌 1. `record` Nedir?

`record`, Java 14 ile gelen, Java 16+ ve sonrasında stabil hale gelen, **veri taşıma (data carrier)** amacıyla kullanılan bir sınıf yapısıdır. Java 21’de aktif şekilde kullanılır.

```java
public record OrderItem(String name, int quantity, double unitPrice) {}
```

Yukarıdaki `record`, otomatik olarak şunları oluşturur:

- `final` field’lar (değiştirilemez alanlar)
- Constructor
- Getter metotları
- `toString()`, `equals()`, `hashCode()` metotları

---

## ✅ 2. Neden Record Kullanılır?

- **Boilerplate (tekrar eden) kodlardan kurtulmak için**  
- **Sadece veri taşımak istiyorsan** (DTO gibi)
- **Immutable (değiştirilemez) nesneler istiyorsan**
- Daha sade, okunabilir, fonksiyonel Java kodları için

---

## 📦 3. Record ve Normal Class Karşılaştırması

### 📌 Klasik Java Class:

```java
public class OrderItem {
    private final String name;
    private final int quantity;
    private final double unitPrice;

    public OrderItem(String name, int quantity, double unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    @Override
    public boolean equals(Object o) { /* ... */ }
    @Override
    public int hashCode() { /* ... */ }
    @Override
    public String toString() { /* ... */ }
}
```

### ✅ Aynı işlemi yapan Record:

```java
public record OrderItem(String name, int quantity, double unitPrice) {}
```

> Görüldüğü gibi `record`, bu kadar kodu tek satıra indiriyor.

---

## 🔍 4. Record ile Metot Tanımlamak

Record sınıflarına **custom metotlar** da yazabilirsin.

```java
public record OrderItem(String name, int quantity, double unitPrice) {

    public double totalPrice() {
        return quantity * unitPrice;
    }
}
```

---

## 🧪 5. Record Kullanımı ile Test

```java
OrderItem item = new OrderItem("Book", 2, 10.0);
System.out.println(item.name());         // -> Book
System.out.println(item.totalPrice());   // -> 20.0
```

> Getter metotları field adlarıyla aynıdır (`item.name()` gibi).

---

## 🛡️ 6. Record'lar Immutable’dır

Aşağıdaki gibi değer değiştiremezsin:

```java
OrderItem item = new OrderItem("Pen", 1, 5.0);
item.name = "Notebook"; // ❌ Compile error!
```

---

## 🎯 7. Record Ne Zaman Kullanılmaz?

- Karmaşık davranışlar içeren sınıflarda
- Kalıtım gereken yerlerde (`record` sınıflar `final`dır)
- Alanları değiştirilebilir olması gereken sınıflarda

---

## 🛠 Gerçek Hayat Örneği: Sipariş Sistemi

```java
public record Order(List<OrderItem> items) {}

public record OrderItem(String name, int quantity, double unitPrice) {
    public double totalPrice() {
        return quantity * unitPrice;
    }
}
```

Burada:
- `OrderItem`: veri taşıma sınıfı
- `Order`: birden fazla item tutan container

Bunlar sadece veri tuttuğu için `record` mükemmel bir tercihtir.

---

## 📚 Kapanış

`record`, veri sınıfları (DTO, Entity-like) için:

- Daha sade
- Daha güvenli
- Daha modern

bir yapı sunar.
