
# 🧪 Java SE Unit Test 1 - Başlangıç Seviyesi

Bu doküman, **Java SE** (Java Standard Edition) ile **JUnit 5** kullanarak nasıl birim testi yazacağınızı adım adım gösterir. Bu örnekler, **Java 21** kullanılarak hazırlanmıştır ve hiçbir test bilgisi olmayan kişiler için uygundur.

---

## 🚀 Başlarken

### 1. Java 21 ve Maven Kurulu Mu Kontrol Et

```bash
java -version
mvn -version
```

> Eğer kurulu değilse:
> - Java için: [https://sdkman.io](https://sdkman.io)
> - Maven için: `brew install maven` (Mac)

---

## 📁 Proje Oluşturma

```bash
mvn archetype:generate -DgroupId=com.learn.test \
-DartifactId=java21-unit-test \
-DarchetypeArtifactId=maven-archetype-quickstart \
-DinteractiveMode=false

cd java21-unit-test
```

---

## ⚙️ pom.xml Ayarları

```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
</properties>

<dependencies>
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

## 📄 Örnek Kod

### Calculator.java

`src/main/java/com/learn/test/Calculator.java`

```java
package com.learn.test;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
```

### CalculatorTest.java

`src/test/java/com/learn/test/CalculatorTest.java`

```java
package com.learn.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(3, 4);
        assertEquals(7, result);
    }
}
```

---

## ▶️ Testleri Çalıştırma

```bash
mvn clean test
```

Başarılı bir test sonucu:
```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: ...
```

---

## ✅ Özet

- Java SE 21 ile çalışıyoruz.
- JUnit 5 kullanıyoruz.
- Maven ile proje yönetiyoruz.
- Terminal üzerinden testleri çalıştırıyoruz.

---

## 📘 İleri Seviye

- Mockito ile Mock nesneleri
- Coverage metrikleri
- Test lifecycle yönetimi
- Exception testleri

---

Hazırlayan: **ChatGPT ile Öğren**
