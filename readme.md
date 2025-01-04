# Mezunlar Platformu
 
![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

## Genel Bakış
**Mezunlar Platformu**, Spring Boot backend ve ReactJS frontend ile geliştirilmiş tam kapsamlı bir web uygulamasıdır. Bu platform, üniversite sınıfları ve mezunlar için özel olarak tasarlanmış bir sosyal paylaşım platformudur. Kullanıcılar mezun arkadaşlarıyla bağlantı kurabilir, etkinlikler düzenleyebilir ve topluluk tartışmalarına katılabilirler.

## İçerik
- [Özellikler](#özellikler)
- [Teknolojiler](#teknolojiler)
- [Kurulum](#kurulum)
- [Kullanım](#kullanım)
- [Katkıda Bulunma](#katkıda-bulunma)
- [Lisans](#lisans)
- [İletişim](#iletişim)

## Özellikler
- **Kullanıcı Doğrulama**: Kayıt olma, giriş yapma ve hesap yönetimi
- **Forum Sistemi**: Kullanıcılar arası iletişim ve tartışma ortamı
- **Kişisel Profil Sayfaları**: Her kullanıcı için özel profil sayfası
- **Fotoğraf ve Video Paylaşımı**: Medya paylaşım özellikleri
- **Harita Entegrasyonu**: Mezunların konumlarını görüntüleme
- **Çoklu Dil Desteği**: Türkçe ve İngilizce dil seçenekleri
- **Responsive Tasarım**: Tüm cihazlarda uyumlu görüntüleme

## Teknolojiler
### Frontend
- **ReactJS**: A JavaScript library for building user interfaces.
- **Vite**: A build tool that provides a faster and leaner development experience for modern web projects.
- **Redux Toolkit**: A predictable state container for JavaScript apps.
- **Axios**: A promise-based HTTP client for the browser and Node.js.
- **Bootstrap**: A CSS framework for developing responsive and mobile-first websites.
- **React-i18next**: Internationalization for React done right.
- **React-Redux**: Official React bindings for Redux.
- **React-Toastify**: Allows you to add notifications to your app with ease.
- **React-Dom**: Provides DOM-specific methods that can be used at the top level of a web app.
- **React-Router-Dom**: DOM bindings for React Router.

### Backend
- **Spring Boot**: A framework for building production-ready applications quickly.
- **Spring Data JPA**: A part of the larger Spring Data family, makes it easy to implement JPA-based repositories.
- **Spring Security**: A powerful and highly customizable authentication and access-control framework.
- **Spring Boot Actuator**: Adds production-ready features to help you monitor and manage your application.
- **Spring Boot DevTools**: Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
- **H2 Database**: An in-memory database, useful for development and testing.
- **ModelMapper**: An intelligent, convention-based object mapper.
- **Tika**: A content analysis toolkit.
- **Log4j2**: A logging framework.
- **Thymeleaf**: A modern server-side Java template engine for web and standalone environments.

### DevOps
- **Docker**: A tool designed to make it easier to create, deploy, and run applications by using containers.
- **Docker Compose**: A tool for defining and running multi-container Docker applications.

### Database
- **H2DB**: An in-memory database, useful for development and testing.
- **Redis**: An in-memory data structure store, used as a database, cache, and message broker.

## Kurulum
### Gereksinimler
- Java 17
- Node.js & npm

### Backend Kurulumu
1. Depoyu klonlayın:
    ```sh
    git clone https://github.com/tokayOzgur/sabis-mezunlar.git
    cd sabis-mezunlar/ws
    ```

2. Projeyi derleyin:
    ```sh
    ./mvnw clean install
    ```

3. Uygulamayı çalıştırın:
    ```sh
    ./mvnw spring-boot:run
    ```

### Frontend Kurulumu
1. Frontend dizinine gidin:
    ```sh
    cd ../frontend
    ```

2. Bağımlılıkları yükleyin:
    ```sh
    npm install
    ```

3. Geliştirme sunucusunu başlatın:
    ```sh
    npm run dev
    ```

## Kullanım
1. Tarayıcınızda `http://localhost:5173` adresine giderek uygulamaya erişebilirsiniz.
2. Backend API `http://localhost:8080` adresinde çalışmaktadır.
3. H2 Veritabanı Konsolu `http://localhost:8080/h2-console/login.jsp` adresinde bulunmaktadır.

## EBT 522 - İleri Web Programlama Yıliçi Proje Ödevi
Bu proje, EBT 522 - İleri Web Programlama dersi kapsamında yıliçi proje ödevi olarak geliştirilmiştir.

### Proje Ekibi
- Özgür Tokay
- Saniye Yurt

### Proje Kapsamı
Proje, üniversite sınıfları için yıllık/mezunlar platformu olarak tasarlanmış olup aşağıdaki temel özellikleri içermektedir:
- Anasayfa
- Kişi Kayıt ve Oturum Açma Sistemi
- Forum
- Kişiler Sayfası ve Profil Yönetimi
- Fotoğraf Paylaşım Sistemi
- Video Paylaşım Sistemi
- Kim, Nerede, Ne Yapıyor? Özelliği
- Harita Entegrasyonu

## Katkıda Bulunma
Topluluktan katkılar memnuniyetle karşılanır! Bu projeye katkıda bulunmak için lütfen aşağıdaki adımları izleyin:

1. **Depoyu çatallayın**:
   - Depo sayfasının sağ üst köşesindeki "Fork" düğmesine tıklayın.

2. **Özellik dalınızı oluşturun**:
   - Çatalladığınız depoyu yerel makinenize klonlayın:
     ```sh
     git clone https://github.com/your-username/your-repo.git
     cd your-repo
     ```
   - Özelliğiniz veya hata düzeltmeniz için yeni bir dal oluşturun:
     ```sh
     git checkout -b feature/AmazingFeature
     ```

3. **Değişikliklerinizi işleyin**:
   - Değişikliklerinizi yapın ve açıklayıcı bir işleme mesajı ile işleyin:
     ```sh
     git add .
     git commit -m 'Add some AmazingFeature'
     ```

4. **Dala itme**:
   - Değişikliklerinizi çatalladığınız depoya itin:
     ```sh
     git push origin feature/AmazingFeature
     ```

5. **Bir Çekme İsteği Açın**:
   - GitHub'da orijinal depoya gidin ve "New Pull Request" düğmesine tıklayın.
   - Az önce ittiğiniz dalı seçin ve inceleme için çekme isteğini gönderin.

Katkınız için teşekkürler!

## Lisans
MIT Lisansı altında dağıtılmaktadır. Daha fazla bilgi için `LICENSE` dosyasına bakınız.

## İletişim
Özgür Tokay - [ozytky@gmail.com](mailto:ozytky@gmail.com)
Saniye Yurt - [saniye.yurt@example.com](mailto:saniyeyurttt@gmail.com)

Proje Bağlantısı: [https://github.com/tokayOzgur/Mezunlar-Platformu](https://github.com/tokayOzgur/Mezunlar-Platformu)
