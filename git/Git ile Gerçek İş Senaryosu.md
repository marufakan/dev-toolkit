
# 📚 Git & GitHub Komutları ile Gerçek İş Senaryosu

## 🚀 Senaryo: "Kullanıcı Giriş Sistemi Geliştirme"

**Proje Adı:** `company-user-portal`  
**Senaryo Açıklaması:** Şirket içi bir portal için kullanıcı giriş sistemini geliştireceğiz. Bu özelliği eklerken, **feature branch** kullanarak geliştirecek ve **Pull Request (PR)** ile GitHub’a göndereceğiz. PR’ı takım arkadaşların inceleyecek ve merge edilmesini sağlayacak.

---

## 🧱 1. **Repo'yu Klonlamak**

Başlamak için, şirketin GitHub repo’sunu kendi bilgisayarına klonlaman gerekiyor.

### Adımlar:
1. GitHub’da projeni bul (örnek repo linki: `https://github.com/company/company-user-portal`).
2. Sağ üstteki **Code** butonuna tıkla ve **HTTPS** kısmındaki linki kopyala.
3. **Git Bash** aç ve projenin bulunduğu dizine git (veya yeni bir dizin oluştur).

```bash
# Yeni dizine git (veya yeni dizin oluştur)
cd /path/to/your/directory

# Repo'yu klonla
git clone https://github.com/company/company-user-portal.git

# Repo dizinine geç
cd company-user-portal
```

### Not:
- **`git clone`** komutu, projeyi yerel bilgisayarına çeker ve ilgili tüm branch'leri indirir.

---

## 🌿 2. **Yeni Bir Feature Branch Oluşturmak**

Yeni bir özellik eklemek için, mevcut `main` branch’inden yeni bir branch oluşturman gerekiyor. Bu sayede üzerinde çalıştığın özellik `main` branch'inden bağımsız olur ve daha sonra düzgün bir şekilde birleştirilebilir.

### Adımlar:
1. **Main branch**'e geç:
```bash
git checkout main
```

2. **Main branch**'i güncelle:
```bash
git pull origin main
```

3. Yeni bir branch oluştur ve ona geç:
```bash
git checkout -b feature/login-page
```
- **`-b`** parametresi, yeni bir branch oluşturmanı sağlar.

**Not:** Branch adını anlamlı bir şekilde koymaya çalış. Bu senaryoda `feature/login-page` olarak adlandırdık.

---

## ✏️ 3. **Geliştirme Yapmak ve Değişiklikleri Commit Etmek**

Yeni login sayfasını eklemek için gerekli dosyaları oluştur ve düzenle. Örneğin:
- `LoginController.java`
- `login.html`

Geliştirmeyi tamamladıktan sonra, bu dosyaları Git ile takip etmeye başlayacağız.

### Adımlar:
1. **Değişikliklerin durumunu kontrol et:**
```bash
git status
```

2. **Yapılan değişiklikleri sahneye al (staging area):**
```bash
git add .
```
- **`git add .`** komutu, tüm değişiklikleri ekler. Eğer sadece belirli dosyaları eklemek istersen, `git add <dosya_adı>` komutunu kullanabilirsin.

3. **Değişiklikleri commit et:**
```bash
git commit -m "feat: login page added"
```
- **`-m`** ile commit mesajı yazılır. Mesajın kısa ve anlamlı olmasına özen göster.

---

## 🔼 4. **Değişiklikleri GitHub'a Push Etmek**

Yerel bilgisayarındaki değişiklikleri GitHub’a göndermek için **push** komutunu kullanman gerekiyor.

### Adımlar:
1. **Değişiklikleri uzak repo’ya gönder:**
```bash
git push origin feature/login-page
```
- **`origin`** uzak repo’nun adı (GitHub’daki repo).
- **`feature/login-page`** ise gönderdiğin branch’in adı.

---

## 🔀 5. **GitHub’da Pull Request (PR) Açmak**

GitHub üzerinde yapılan branch değişikliklerinin **main** branch ile birleştirilmesi için **Pull Request** açılmalıdır.

### Adımlar:
1. GitHub’a giriş yap ve projenin sayfasına git.
2. `feature/login-page` branch’ini seç.
3. Sağ üstte **`Compare & Pull Request`** butonuna tıkla.
4. **Başlık ve açıklama** kısmını doldur:
   - **Başlık:** `Login Page Feature`
   - **Açıklama:**

```markdown
## Açıklama
- Kullanıcıların giriş yapabileceği login sayfası eklendi.
- Giriş formu, controller ve basit doğrulama içeriyor.

## Test
- Tarayıcıda localhost:8080/login üzerinden test edildi.
```

5. **Review** (kod inceleme) için takım arkadaşlarını etiketleyebilirsin.

6. PR’ı gönder ve takım arkadaşlarının onayını bekle.

---

## ✔️ 6. **PR Merge Edildikten Sonra Yapılması Gerekenler**

Pull Request onaylandıktan sonra, **main branch**’e merge edilmesi gerekir. Bu işlem sonrasında yapılacak temizlik işlemleri de vardır.

### Adımlar:
1. **Main branch’ine geç:**
```bash
git checkout main
```

2. **Main branch’i güncelle:**
```bash
git pull origin main
```

3. **Feature branch’ini sil:**
```bash
git branch -d feature/login-page
```

4. **Uzak branch’i sil:**
```bash
git push origin --delete feature/login-page
```

---

## 🧪 7. **Test ve Geri Dönüş**

Eğer bir hata fark edersen ve yeniden düzenleme yapman gerekirse:
1. **Tekrar feature branch’ine geç**:
```bash
git checkout -b feature/fix-login-validation
```

2. Hataları düzelt ve değişiklikleri commit et:
```bash
git add .
git commit -m "fix: login validation bug"
git push origin feature/fix-login-validation
```

3. Yeni bir PR açarak, tekrar merge işlemi yapabilirsin.

---

## 📦 8. **.gitignore Dosyası (Örnek)**

Çoğu zaman projelerde takip edilmemesi gereken dosyalar olur (örneğin, derleme dosyaları veya ortam dosyaları). Bunun için `.gitignore` dosyası kullanılır.

**Örnek .gitignore**:

```
# Java derleme dosyaları
/target/
*.class

# IDE dosyaları
/.idea/
/.vscode/

# Ortam dosyaları
*.env
```

---

## 🎯 Sonuç

Bu adımlarla, bir **feature branch** üzerinde değişiklik yapıp, **Pull Request** açarak GitHub üzerinden projede ilerlemiş oldun. Git ve GitHub’ı günlük iş akışlarında kullanmaya başladıkça daha verimli olacaksın!

---
