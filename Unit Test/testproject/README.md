# Hakkında
# 📄 Ticari Bankacılık - Kredi Başvuru Servisi (Loan Application Service)

Bu proje, ticari bankacılık alanında bir müşterinin kredi başvurusunu işlemek için tasarlanmış örnek bir Java SE 8 uygulamasıdır. Kodlar Java 6 uyumlu olacak şekilde yazılmıştır ve birim testleriyle desteklenmiştir.

## 🎯 Projenin Amacı

- Kredi başvuru sürecini simüle etmek
- Kredi notuna göre başvuruyu onaylama veya reddetme
- Gerçek dünya senaryolarında karşılaşılabilecek durumları (örneğin sistem hataları) simüle etmek
- Unit Test ve Mockito kullanarak test altyapısı kurmak
- JaCoCo ile test kapsamını analiz etmek

## 🛠️ Kullanılan Teknolojiler ve Araçlar

| Teknoloji / Araç         | Açıklama                             |
|--------------------------|--------------------------------------|
| Java SE 8                | Ana programlama dili (Java 6 uyumlu) |
| JUnit 4                  | Unit test framework'ü                |
| Mockito                  | Mocking (taklit nesne) kütüphanesi   |
| JaCoCo                   | Test kapsamı ölçüm aracı             |
| Maven                    | Bağımlılık yönetimi ve build tool    |
| IntelliJ IDEA / VS Code  | Önerilen IDE                         |

## 📦 Proje Bağımlılıkları (pom.xml örneği)

```xml
<dependencies>
    <!-- JUnit 4 -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <!-- Mockito -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>3.12.4</version>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <!-- JaCoCo Plugin -->
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.8</version>
            <executions>
                <execution>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <execution>
                    <id>report</id>
                    <phase>test</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## 📁 Proje Yapısı

```
src/
└── main/
    └── java/
        └── model/
            └── Customer.java
        └── service/
            └── CreditScoreService.java
            └── LoanApplicationService.java
└── test/
    └── java/
        └── service/
            └── LoanApplicationServiceTest.java
```

## ✅ Öne Çıkan Özellikler

- `LoanApplicationService`: Kredi puanına göre başvuruyu değerlendirir.
- `CreditScoreService`: Müşterinin kredi notunu dış servisten alıyormuş gibi davranır.
- **Retry mekanizması**: Servis başarısız olursa 3 denemeye kadar tekrarlar.
- **Mockito** ile:
    - `when().thenReturn()` ve `thenThrow()` kullanımı
    - `verify()` ile doğrulama
- **JUnit** ile:
    - `@Test`, `@Before`, `@After` anotasyonları
    - `assertEquals`, `assertThrows` gibi assertions
- **JaCoCo** ile:
    - Kapsamlı test raporları
    - Kod satırı ve blok seviyesinde coverage takibi

## 📊 JaCoCo Raporu

Test sonrası coverage raporları otomatik olarak `target/site/jacoco/index.html` dosyasında oluşur. Raporu açmak için terminale aşağıdaki komutu yazabilirsiniz:

```bash
open target/site/jacoco/index.html
```

## 🧪 Test Kapsamı

- Kredi notu yüksekse "Loan Approved" mesajı döner.
- Kredi notu düşükse "Loan Rejected" mesajı döner.
- Dış servis başarısız olursa retry edilir ve sonunda exception fırlatılır.
- `InterruptedException` yönetimi testlerde ele alınır.

## 📚 Öğrenilen Konular

- Java ile temiz ve basit iş servisi tasarlama
- Test Driven Development (TDD) yaklaşımı
- Mockito ile testlerde dış servislerin simülasyonu
- JUnit lifecycle anotasyonları (`@Before`, `@After`)
- JaCoCo ile test kapsamı analizi


---

# V1

🎯 Hedefimiz:
Bir bankacılık projesinin “Kredi Puanı Kontrolü” modülünü yapacağız.
Testleri JUnit 4 + Mockito 1.10.19 ile yazacağız.

Projeye dahil olacak konular:
✅ Java sınıfları

✅ JUnit ile @Test, @Before, @After

✅ Mockito ile:

mock(), when().thenReturn()

when().thenThrow()

verify()

✅ Exception test etme

✅ Terminalden çalıştırma

✅ Projeyi dizin yapısı ile oluşturma

----------------------
# V2

Harika! Şimdi projeyi bir üst seviyeye taşıyalım. Yeni bir servis sınıfı ekleyelim:
📌 LoanApplicationService – Bu servis kredi başvurusunu değerlendirir.

Kredi puanı 700 ve üzeriyse başvuru onaylanır, aksi halde reddedilir.

## 🧪 3. Yeni Özelliklerle Öğrendiğimiz Mockito Konuları

### Özellikler ve Kullanımları

| Özellik                  | Açıklama                              | Kullanımı                            |
|--------------------------|----------------------------------------|---------------------------------------|
| `when().thenReturn()`    | Mock nesneye cevap tanımlama           | `when(service.getX()).thenReturn(...)` |
| `when().thenThrow()`     | Hata fırlatılmasını simüle etme        | `when(service.getX()).thenThrow(...)`  |
| `verify()`               | Metodun çağrıldığını doğrulama         | `verify(service).method(...)`         |
| `verify(..., times(n))`  | Kaç kez çağrıldığını doğrulama         | `verify(service, times(1))`           |
| `assertTrue` / `assertFalse` | Sonucu kontrol etme              | `assertTrue(value)` / `assertFalse(value)` |


### 🔍 `verify(mockCreditScoreService, times(1)).getCreditScore(customer);` Ne Anlama Geliyor?

Bu ifade, **Mockito** test kütüphanesi kullanılarak yazılmış bir birim test doğrulamasıdır.  
Amacı, bir **mock (sahte) nesnenin** belirli bir metodunun test sırasında **belirli sayıda çağrıldığını kontrol etmektir.**

---

### 🧩 Parça Parça Açıklama

| Parça                         | Açıklama                                                                 |
|-------------------------------|--------------------------------------------------------------------------|
| `verify(...)`                 | Mockito'nun doğrulama (verification) metodudur. Belirtilen mock nesne üzerinde bir metodun çağrılıp çağrılmadığını kontrol eder. |
| `mockCreditScoreService`      | Testte kullanılan sahte (mock) servis nesnesidir. Gerçek servis yerine test amacıyla oluşturulmuş taklit bir nesnedir. |
| `times(1)`                    | Bu metodun **tam olarak 1 kez** çağrıldığını kontrol eder. (Eğer hiç ya da 2+ kez çağrıldıysa test başarısız olur.) |
| `.getCreditScore(customer)`   | Doğrulamak istediğimiz metodun **hangi parametreyle** çağrıldığını belirtir. |


✅ Sonuç
Projeye şunları ekledik:

Yeni servis (LoanApplicationService)

Gerçek hayata yakın karar yapısı

Daha fazla Mockito yeteneği (exception, verify, edge case)

Unit test kapsamı genişledi

------------------------------
# V3

# ✅ Java SE 8 - JaCoCo ile Test Coverage Ölçümü

Bu doküman, Java SE 8 kullanılarak geliştirilen Maven tabanlı projelerde **test coverage (kapsama oranı)** ölçümünü sağlamak için **JaCoCo** eklentisinin nasıl entegre edileceğini ve kullanılacağını açıklar.

---

## 🔍 JaCoCo Nedir?

**JaCoCo (Java Code Coverage Library)**, unit test'lerinizin uygulama kodunuzun ne kadarını test ettiğini ölçen bir araçtır. Kodunuzun ne kadarının test edildiğini yüzdelik olarak gösterir.

---

## ⚙️ Maven Projesine Ekleme

`pom.xml` dosyanızın `<build>` bloğu içerisine aşağıdaki yapı eklenmelidir:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.jacoco</groupId>
      <artifactId>jacoco-maven-plugin</artifactId>
      <version>0.8.10</version>
      <executions>
        <execution>
          <goals>
            <goal>prepare-agent</goal>
          </goals>
        </execution>
        <execution>
          <id>report</id>
          <phase>prepare-package</phase>
          <goals>
            <goal>report</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

---

## 🚀 Kullanım

### ✅ Testleri çalıştırmak için:
```bash
mvn clean test
```

### 📊 Coverage raporu üretmek için:
```bash
mvn clean verify
```

### 📂 Raporu Görüntülemek (Mac için):
```bash
open target/site/jacoco/index.html
```

---

## 📌 Notlar
- Rapor, `target/site/jacoco/` klasöründe `index.html` olarak oluşturulur.
- Unit test kapsamı yetersizse, bu rapor sayesinde hangi sınıfların/testlerin eksik olduğunu görebilirsiniz.
- Continuous Integration (CI) sistemleriyle entegre şekilde çalışabilir.

---

## 🧪 Örnek Kullanım Senaryosu

Bir bankacılık projesinde kredi skoru değerlendirme servisini test ederken, hangi sınıfın/testin ne kadar çalıştığını JaCoCo ile ölçüp eksik testleri tamamlayabilirsiniz.


---
# v4
## 🚀 1. LoanApplicationService'de Dış Servise Bağlantı ve Timeout Senaryosu

Bir dış servise bağlanıyoruz (örneğin, kredi puanı almak için bir başka mikro hizmet) ve bu servisin zaman aşımına uğraması gibi senaryoları ele alacağız.  
Bu, genellikle mikro servis mimarilerinde karşılaşılan **gerçek bir durumdur**.

---

## ✨ 2. Yeni Özellikler

### ⏱️ Zaman Aşımı Senaryosu
Kredi puan servisi zaman aşımına uğrarsa, `LoanApplicationService` bir hata fırlatacak.

### ✅ Başarı Durumunda, Kredi Onay Durumu
Kredi başvurusu başarılı olduğunda, sistem bir **"onay" mesajı** dönecek.

### 🔁 Retry Mantığı
Zaman aşımından sonra `LoanApplicationService`, servisi **bir kez daha tekrar deneyecek** (toplamda maksimum **3 defa**).

## 🧪 5. Testlerde Kullanılan Yeni Özellikler

### ❗ `when().thenThrow()` ile Exception Fırlatma

Bu özellik, bir metodun belirli bir durumda **hata fırlatmasını simüle eder**.  
Örneğin, `creditScoreService.getCreditScore(customer)` çağrısının bir hata fırlatmasını sağlıyoruz:

```java
when(mockCreditScoreService.getCreditScore(customer))
    .thenThrow(new RuntimeException("Service unavailable"));
```

---

### ✅ `verify()` ile Çağrı Sayısını Kontrol Etme

Bu özellik, bir metodun **belirli bir sayıda çağrılıp çağrılmadığını** kontrol eder.  
Örneğin, kredi puanı servisi **3 kez çağrılmalı**, çünkü retry mekanizması devrede:

```java
verify(mockCreditScoreService, times(3)).getCreditScore(customer);
```

---

### ⏱️ `TimeUnit.SECONDS.sleep()` ile Bekleme

Bir **zaman aşımı durumu** simüle ediyoruz.  
Yani servisin **2 saniye bekledikten sonra** yeniden denemesi gerektiğini test ediyoruz:

```java
TimeUnit.SECONDS.sleep(2); // Retry after 2 seconds
```

---

### 🧯 `@Test(expected = RuntimeException.class)` ile Hata Bekleme

Testlerin bir **exception fırlatıp fırlatmadığını kontrol etmek** için `@Test(expected = ...)` annotation'ı kullanılır.  
Bu, özellikle exception senaryoları test ederken oldukça kullanışlıdır:

```java
@Test(expected = RuntimeException.class)
public void shouldThrowExceptionWhenServiceFails() {
    // test içeriği
}
```

# V5
Tabii! İşte örneklerle desteklenmiş, kısa ama açıklayıcı bir şekilde TDD’yi anlatan bir `README` bölümü:

---

## 🧪 Test Driven Development (TDD)
1. **Önce test yazıldı, sonra kod geliştirildi.**
2. Kod, yalnızca testi geçecek kadar yazıldı.
3. Gerekiyorsa refactor edildi.

### 🔁 Süreç Akışı:

1. **Test Senaryosu Yazıldı**
   ```java
   @Test
   public void testLoanApproved_WhenScoreIsHigh() {
       when(mockCreditScoreService.getCreditScore(customer)).thenReturn(720.0);
       String result = loanApplicationService.applyForLoan(customer);
       assertEquals("Loan Approved", result);
   }
   ```

2. **İlgili İş Mantığı Yazıldı**
   ```java
   public String applyForLoan(Customer customer) {
       double score = creditScoreService.getCreditScore(customer);
       return (score >= 700) ? "Loan Approved" : "Loan Rejected";
   }
   ```

3. **Gelişmiş Testler Eklendi (Mockito, Exception Handling)**
   ```java
   @Test(expected = RuntimeException.class)
   public void testLoanServiceUnavailableAfterRetries() throws InterruptedException {
       when(mockCreditScoreService.getCreditScore(customer)).thenThrow(new RuntimeException("Service down"));

       loanApplicationService.applyForLoan(customer); // 3 kez dener, sonra throw eder
   }
   ```

### ✅ Kullanılan Özellikler:

- `JUnit 4`: Test frameworkü
- `Mockito`: Dış bağımlılıkları (örneğin servisleri) sahte/mock nesneyle değiştirme
- `@Before`, `@After`: Her test öncesi/sonrası setup işlemleri
- `verify()`, `thenThrow()`: Mock davranışlarını kontrol etme
- `assertEquals`, `expected`: Test doğrulama ve exception kontrolü

---