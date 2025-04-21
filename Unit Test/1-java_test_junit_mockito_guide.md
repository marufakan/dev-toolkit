
# 🧪 Java'da Test Yazımı: JUnit ve Mockito Rehberi

Bu döküman, Java'da birim testleri yazmak isteyen ve JUnit ile Mockito'yu sıfırdan öğrenmek isteyenler için hazırlanmıştır.

---

## 📌 Test Nedir?

Test, yazdığın kodun **doğru çalışıp çalışmadığını kontrol etmek** için yazdığın başka bir koddur.

> Yani: “Ben bu sınıfı yazdım. Gerçekten doğru çalışıyor mu?” sorusuna cevap veririz.

---

## 🧪 1. JUnit Nedir?

**JUnit**, Java'da **birim testleri** (unit tests) yazmak için kullanılan en popüler kütüphanedir.

### 🧠 Basit Mantıkla JUnit:
- Bir sınıfı test etmek istiyoruz.
- Bir fonksiyonun girdiğine göre doğru çıktıyı verip vermediğini kontrol ediyoruz.

### 🧱 Kurulum (Maven için)

```xml
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.9.3</version>
  <scope>test</scope>
</dependency>
```

### 🧪 Örnek: Hesap Makinesi Sınıfını Test Edelim

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
```

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result); // Beklenen 5 mi?
    }
}
```

✅ Bu test başarılıysa, fonksiyon doğru çalışıyor demektir.

❌ Eğer sonuç 5 değilse test fail olur.

---

## 🤖 2. Mockito Nedir?

**Mockito**, bir sınıfın içindeki başka sınıflara olan bağımlılıkları **taklit** (mock) etmemizi sağlar.

> Yani: “Bu servisin içinde başka bir servis kullanılıyor ama biz o servisi test etmek istemiyoruz.” diyorsan → Mockito kullanırsın.

### 🎯 Gerçek Hayat Örneği

```java
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserNameById(Long id) {
        User user = userRepository.findById(id);
        return user.getName();
    }
}
```

### 🔧 Mockito ile Test Edelim

```xml
<dependency>
  <groupId>org.mockito</groupId>
  <artifactId>mockito-core</artifactId>
  <version>5.2.0</version>
  <scope>test</scope>
</dependency>
```

```java
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    public void testGetUserNameById() {
        UserRepository mockRepo = mock(UserRepository.class);
        User mockUser = new User(1L, "Alice");
        when(mockRepo.findById(1L)).thenReturn(mockUser);

        UserService userService = new UserService(mockRepo);
        String userName = userService.getUserNameById(1L);

        assertEquals("Alice", userName);
    }
}
```

---

## 🧩 JUnit ve Mockito Nerelerde Kullanılır?

| Durum | Kullanılacak Araç |
|------|-------------------|
| Fonksiyon doğru sonuç veriyor mu? | ✅ JUnit |
| Sınıf içindeki bağımlılığı taklit et | ✅ Mockito |
| Veritabanı, e-posta, servis çağrısı varsa ama gerçek işlem yapma | ✅ Mockito |

---

## 🧠 Akılda Kalması İçin

- **JUnit = Test framework'ü** → Kod doğru çalışıyor mu, kontrol eder.
- **Mockito = Taklitçi (Mocking Tool)** → Sınıf içindeki dış bağımlılıkları sahte hale getirir.
- **assertEquals(expected, actual)** → Beklenen sonuçla, gelen sonuç eşleşiyor mu?

---
