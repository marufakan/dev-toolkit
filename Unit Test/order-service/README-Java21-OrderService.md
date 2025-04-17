# 🧪 Java 21 + JUnit 5 ile Order Service Test Projesi

Bu proje, Java 21 kullanarak basit bir `OrderService` sınıfını test etmeyi amaçlayan bir örnektir. Testler JUnit 5 kullanılarak yazılmıştır.

---

## 📁 Proje Yapısı

```
order-service/
├── pom.xml
├── src
│   ├── main/java/com/example/order/
│   │   ├── Order.java
│   │   ├── OrderItem.java
│   │   └── OrderService.java
│   └── test/java/com/example/order/
│       └── OrderServiceTest.java
```

---

## 🧰 Gereksinimler

- Java 21 (SDKMAN ile kurulabilir)
- Maven
- Terminal (Mac OS - Zsh veya Bash)

---

## 🧱 1. Java 21 Kurulumu (SDKMAN ile)

```bash
/bin/bash -c "$(curl -fsSL https://get.sdkman.io)"
source ~/.zshrc   # veya ~/.bash_profile

sdk install java 21-tem
sdk use java 21-tem
```

---

## 🔨 2. Maven Kurulumu

```bash
brew install maven
```

---

## 🏗️ 3. Maven Projesi Oluşturma

```bash
mvn archetype:generate -DgroupId=com.example.order \
-DartifactId=order-service \
-DarchetypeArtifactId=maven-archetype-quickstart \
-DinteractiveMode=false

cd order-service
```

---

## 🧾 4. `pom.xml` Düzenle

JUnit 5'i eklemek için:

```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
</properties>

<dependencies>
    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
        </plugin>
    </plugins>
</build>
```

---

## 📄 5. Kod Dosyaları

### `OrderItem.java`

```java
package com.example.order;

public record OrderItem(String name, int quantity, double unitPrice) {
    public double totalPrice() {
        return quantity * unitPrice;
    }
}
```

### `Order.java`

```java
package com.example.order;

import java.util.List;

public record Order(List<OrderItem> items) {}
```

### `OrderService.java`

```java
package com.example.order;

import java.util.Objects;

public class OrderService {

    public double calculateTotal(Order order) {
        if (order == null || order.items() == null || order.items().isEmpty()) {
            throw new IllegalArgumentException("Order is empty or null");
        }

        return order.items().stream()
                .mapToDouble(OrderItem::totalPrice)
                .sum();
    }
}
```

---

## 🧪 6. Test Dosyası

### `OrderServiceTest.java`

```java
package com.example.order;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final OrderService service = new OrderService();

    @Test
    void shouldCalculateTotalCorrectly() {
        Order order = new Order(List.of(
            new OrderItem("Book", 2, 15.0),
            new OrderItem("Pen", 3, 2.5)
        ));

        double total = service.calculateTotal(order);
        assertEquals(15.0 * 2 + 2.5 * 3, total);
    }

    @Test
    void shouldThrowExceptionWhenOrderIsEmpty() {
        Order order = new Order(List.of());
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateTotal(order);
        });
    }

    @Test
    void shouldThrowExceptionWhenOrderIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateTotal(null);
        });
    }
}
```

---

## ▶️ 7. Testleri Çalıştırma

```bash
mvn clean test
```

---

## 🧹 8. Temizlik (Opsiyonel)

```bash
mvn clean
```

---

## ✅ Öğrendiklerin

- Java 21 ile Maven projesi oluşturma
- Record sınıf yapıları ile modern Java
- JUnit 5 ile test yazma
- Exception senaryolarını test etme
