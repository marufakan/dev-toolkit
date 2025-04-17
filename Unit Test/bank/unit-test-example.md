
# 📌 Unit Test Çalışma Mantığı (Java SE 6 uyumlu - Java 8 ortamında)

Bu örnekte, bir bankacılık uygulamasında "kredi tahsisi" süreci test edilmektedir. Test senaryosu basit bir müşteri için kredi puanı üzerinden kredi tahsisinin başarılı olup olmadığını kontrol eder.

## 🔍 Kullanılan Teknolojiler

- **Java 8 (Java 6 uyumlu yazım şekliyle)**
- **JUnit 4**
- **Mockito (Mock nesneler oluşturmak için)**

---

## 🧪 Test Edilen Sınıf: `CreditAllocation`

Bu sınıf, bir müşterinin kredi puanına göre kredi tahsis edip etmeyeceğine karar verir.

```java
public class CreditAllocation {
    private CreditScoreService creditScoreService;

    public CreditAllocation(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }

    public boolean allocateCredit(Customer customer, double creditAmount) {
        double score = creditScoreService.getCreditScore(customer);
        return score >= 600 && customer.getMonthlyIncome() >= creditAmount / 2;
    }
}
```

---

## 🧪 Test Sınıfı: `CreditAllocationTest`

```java
@Test
public void testSuccessfulCreditAllocation() {
    // 1. Adım: CreditScoreService mock nesnesi oluşturuluyor
    CreditScoreService mockCreditScoreService = mock(CreditScoreService.class);

    // 2. Adım: getCreditScore() metoduna her çağrıldığında 650.0 dönmesi sağlanıyor
    when(mockCreditScoreService.getCreditScore(any(Customer.class))).thenReturn(650.0);

    // 3. Adım: Test için sahte müşteri nesnesi oluşturuluyor
    Customer customer = new Customer("Ali Veli", 5000, 20000); // gelir ve limit bilgisi

    // 4. Adım: Test edilecek CreditAllocation sınıfı mock servis ile birlikte enjekte ediliyor
    CreditAllocation creditAllocation = new CreditAllocation(mockCreditScoreService);

    // 5. Adım: allocateCredit() çağrılıyor ve sonucu bir değişkene atanıyor
    boolean result = creditAllocation.allocateCredit(customer, 15000);

    // 6. Adım: Beklenen sonuç assertTrue() ile doğrulanıyor
    assertTrue(result); // Bu işlem başarılı bir kredi tahsisi döndürmeli
}
```

---

## 🔍 Detaylı Açıklama Adım Adım

### ✅ 1. `mock(CreditScoreService.class)`
Bu satır, gerçek `CreditScoreService` yerine onun sahte bir versiyonunu oluşturur. Bu mock nesne test içinde sadece tanımladığımız davranışları sergiler.

### ✅ 2. `when(...).thenReturn(650.0)`
Bu satırda, mock nesneye ait `getCreditScore()` metodu çağrıldığında her zaman `650.0` değerini döndürmesi sağlanır. Böylece testin dışsal etkenlerden etkilenmesi önlenmiş olur.

### ✅ 3. `Customer customer = new Customer(...)`
Testin amacı, bu müşteri bilgilerine göre sistemin kredi tahsis edip etmeyeceğini kontrol etmektir.

### ✅ 4. `CreditAllocation creditAllocation = new CreditAllocation(...)`
Test edeceğimiz sınıfa sahte servisimiz enjekte edilir. Böylece, `CreditAllocation` sınıfı içindeki `creditScoreService` gerçek değil, kontrol ettiğimiz mock nesne olur.

### ✅ 5. `allocateCredit(...)`
Bu metod test edilir. `650` puan ve `5000` aylık gelir ile `15000` kredi istendiğinde, koşullara göre `true` dönmesi beklenir.

- Puan 650 >= 600 ✅
- Gelir >= 15000 / 2 → 5000 >= 7500 ❌ (burada dikkat! Bu örneğe göre tahsis reddedilmeli, test verisi düzeltilmeli.)

Test verisini şöyle güncelleyelim:
```java
Customer customer = new Customer("Ali Veli", 8000, 20000);
```
Böylece test gerçekten "başarılı tahsis" senaryosunu karşılar.

### ✅ 6. `assertTrue(result)`
Bu satır, test sonucunun `true` dönmesini bekler. Eğer `allocateCredit()` sonucu `true` değilse test başarısız olur.

---

## 📁 Alternatif Test: Başarısız Durum

```java
@Test
public void testFailedCreditAllocation_dueToLowScore() {
    CreditScoreService mockService = mock(CreditScoreService.class);
    when(mockService.getCreditScore(any(Customer.class))).thenReturn(550.0);

    Customer customer = new Customer("Ali Veli", 8000, 20000);
    CreditAllocation allocation = new CreditAllocation(mockService);

    boolean result = allocation.allocateCredit(customer, 10000);

    assertFalse(result); // kredi puanı yetersiz
}
```

---

Bu dökümana eklemek isterseniz, örnek proje yapısı, daha fazla negatif test durumu veya `@Before` gibi test setuplarını da anlatabilirim. Devam etmek ister misiniz? 🚀
