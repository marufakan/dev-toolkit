
# 🧪 Java Unit Test Gelişmiş Konular (JUnit & Mockito)

Bu döküman, Mockito ve JUnit kullanarak **gelişmiş test senaryolarını** öğrenmek isteyenler için hazırlanmıştır. Konular sırayla örnekler ve açıklamalarla ele alınmıştır.

---

## ✅ 1. Exception Fırlatma - `thenThrow()`

Mockito ile bir metodun exception fırlatmasını simüle edebilirsin.

### 📌 Senaryo:
Kredi notu alınırken sistemsel bir hata oluyor.

### 💻 Kod:
```java
@Test(expected = RuntimeException.class)
public void testCreditScoreServiceThrowsException() {
    CreditScoreService mockService = mock(CreditScoreService.class);

    when(mockService.getCreditScore(any(Customer.class)))
        .thenThrow(new RuntimeException("System failure"));

    Customer customer = new Customer("Ahmet", "1234567890");

    // Bu satırda exception fırlatılmalı
    mockService.getCreditScore(customer);
}
```

### 💡 Açıklama:
- `thenThrow(new RuntimeException(...))`: Bu metod çağrıldığında exception fırlatır.
- `@Test(expected = ...)`: Bu exception'ın gerçekten fırlatıldığını test eder.

---

## ✅ 2. JUnit Lifecycle Annotations (`@Before`, `@After`, `@BeforeClass`, `@AfterClass`)

Bu anotasyonlar testlerin **öncesinde ve sonrasında** çalışan metodlar tanımlamak için kullanılır.

### 💻 Kod:
```java
public class CreditServiceTest {

    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("▶ Tüm testlerden önce bir kez çalışır.");
    }

    @AfterClass
    public static void afterAllTests() {
        System.out.println("⏹ Tüm testlerden sonra bir kez çalışır.");
    }

    @Before
    public void setUp() {
        System.out.println("🔄 Her testten önce çalışır.");
    }

    @After
    public void tearDown() {
        System.out.println("🧹 Her testten sonra çalışır.");
    }

    @Test
    public void testSomething() {
        System.out.println("✅ Test çalışıyor.");
    }
}
```

### 🧪 Konsol Çıktısı:
```
▶ Tüm testlerden önce bir kez çalışır.
🔄 Her testten önce çalışır.
✅ Test çalışıyor.
🧹 Her testten sonra çalışır.
⏹ Tüm testlerden sonra bir kez çalışır.
```

---

## ✅ 3. `verify()` ile Metod Çağrısını Doğrulama

Mockito ile bir metodun kaç kere ve nasıl çağrıldığını doğrulayabilirsin.

### 💻 Kod:
```java
@Test
public void testVerifyMethodCalled() {
    CreditScoreService mockService = mock(CreditScoreService.class);
    Customer customer = new Customer("Ayşe", "987654321");

    mockService.getCreditScore(customer);
    mockService.getCreditScore(customer);

    verify(mockService, times(2)).getCreditScore(customer);

    verify(mockService, never()).getCreditScore(new Customer("Ali", "123"));
}
```

### 🧠 Faydalı `verify()` Kullanımları:

| Kullanım                | Açıklama                   |
|-------------------------|----------------------------|
| `verify(obj).method()`  | 1 kez çağrıldı mı?         |
| `times(n)`              | n kez çağrıldı mı?         |
| `never()`               | Hiç çağrılmadı mı?         |
| `atLeast(n)`            | En az n kez çağrıldı mı?   |
| `atMost(n)`             | En fazla n kez çağrıldı mı?|

---

## 🔚 Özet Tablosu

| Konu                     | Açıklama                                               |
|--------------------------|--------------------------------------------------------|
| `thenThrow()`            | Exception fırlatan davranışı test eder                |
| `@Before/@After`         | Her testten önce/sonra çalışan setup/cleanup          |
| `@BeforeClass/@AfterClass`| Tüm testlerden önce/sonra bir kez çalışır             |
| `verify()`               | Metodun çağrılıp çağrılmadığını test eder             |

---
