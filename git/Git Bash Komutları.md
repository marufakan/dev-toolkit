
# 📘 Git Bash Komutları ve GitHub PR Süreci Rehberi

Bu döküman, gerçek projelerde ve kurumsal şirket ortamında Git Bash üzerinden kullanabileceğin temel komutları ve GitHub Pull Request (PR) sürecini açıklamaktadır.

---

## 🧱 1. Başlangıç Ayarları

```bash
git config --global user.name "Ad Soyad"
git config --global user.email "email@example.com"
git config --list
```

---

## 📁 2. Repo İşlemleri

### 🔹 Yeni repo başlatma

```bash
git init
```

### 🔹 Var olan repoyu klonlama

```bash
git clone https://github.com/user/repo.git
```

---

## ⚙️ 3. Temel Git Komutları

### ✅ Değişiklikleri takip etme

```bash
git status
git diff
```

### ➕ Dosya ekleme ve commit

```bash
git add dosya_adi
git add .               # Tüm dosyalar
git commit -m "Mesaj"
```

### 🔼 Push & Pull

```bash
git push
git pull
git fetch
```

---

## 🌿 4. Branch (Dal) Yönetimi

```bash
git branch                  # Mevcut branch’leri listeler
git branch yeni-branch      # Yeni branch oluşturur
git checkout yeni-branch    # Branch’e geçer
git checkout -b yeni-branch # Yeni branch oluşturup geçer
git merge branch-adi        # Branch’i mevcut dala merge eder
git branch -d branch-adi    # Branch’i siler
```

---

## ♻️ 5. Versiyon Geri Alma

```bash
git log                      # Commit geçmişi
git reset --hard <commit-id> # Commit’e geri dön
git revert <commit-id>       # Commit’i geri al, ama log’da göster
git checkout -- dosya        # Dosyayı son haline döndür
```

---

## 🔐 6. Remote Repo İşlemleri

```bash
git remote -v
git remote add origin https://github.com/user/repo.git
git push -u origin main
git push origin feature/login
```

---

## 📦 7. Ekstra Komutlar

```bash
git stash           # Değişiklikleri geçici olarak saklar
git stash pop       # Saklanan değişiklikleri geri alır
git clean -fd       # Takip edilmeyen dosyaları siler
git show <commit-id># Commit detaylarını gösterir
```

---

## 🚀 8. GitHub PR (Pull Request) Süreci

### 1. Yeni bir özellik/issue için branch oluştur:
```bash
git checkout -b feature/ekleme-adi
```

### 2. Değişiklik yap, commit ve push:
```bash
git add .
git commit -m "Yeni özellik: giriş ekranı eklendi"
git push origin feature/ekleme-adi
```

### 3. GitHub’a git → Branch’i seç → `Compare & Pull Request` butonuna tıkla.

### 4. PR başlığını ve açıklamasını gir:
- **Ne yaptın?**
- **Hangi issue’yu çözüyor?**
- **Test ettin mi?**

### 5. Review (Kod inceleme) için takım arkadaşlarını etiketle.

### 6. PR onaylandıktan sonra:
```bash
# En son main'e geçip güncelle
git checkout main
git pull origin main

# PR merge edildikten sonra lokal branch’i silebilirsin:
git branch -d feature/ekleme-adi
git push origin --delete feature/ekleme-adi
```

---

## 📄 9. .gitignore Örneği

```
/target
/.idea
*.log
*.class
*.iml
.env
node_modules/
```

---

## ✅ Notlar

- Her özellik için ayrı branch kullan.
- PR açıklamaları açık ve net olsun.
- Commit mesajları anlaşılır, küçük harflerle ve İngilizce yazılmalı (örn: `fix login bug`).
- Merge işlemi öncesi `pull` yaparak kodları güncel tut.

---
