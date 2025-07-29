# Dijital kartvizit 
Bu projeyi geliştirme amacım çalışanların kartlarındaki qr ile her kişiye özel bir url ile kişisel bilgileri barındıran bir site yapmak.Springboot eclipse ile projeyi geliştirdim.
# Uygulamayı çalıştırmak için
git clone https://github.com/sevvalislekter/DigitalCard.git

cd DigitalCard

./mvnw clean install         # Linux/macOS için

mvnw.cmd clean install       # Windows için

./mvnw spring-boot:run       # Uygulamayı çalıştırır

## Proje de kullandığım teknolojiler

Springboot eclipse

Postgresql 

OpenLdap


## Özellikler

-Admin paneliyle çalışan ekleme(ad,soyad,telefon,linkedin url,email,title,fotoğraf)
-Admin panelinde çalışanları listeleme
-Admin panelinde çalışanları gösteren yerde QR resim indirme, Bilgileri güncelleme,Qr pasif etme
-QR pasif etme butonuyla çalışanın Qr kodu etkisiz hale geliyor.
-Bu sayede QR pasif ettiğimiz tarihi de işten çıkış tarihi olarak güncelliyor. 
-Ve en üstte eski çalışanlar olarak eklediğimiz linke tıkladığımızda Qr active false olan çalışanlar ekrana geliyor.
-Qr kod okutulunca kişiye özel çıkan url ile kişisel kartvizit sitesine ulaşmış oluyoruz.Yine içinde kişisel bilgiler , fotoğraf , title ve kişi ekle butonuyla vcf dosya uzantısı açılıyor.



## Yapı



src/
└── main/
    └── java/
        └── com.cardvisit/
        
            ├── controller/
            
            │   ├── EmployeeAdminController
            
            │   ├── LoginController
            
            │   ├── ProfileController
            
            │   └── VcfGenerateCardController
            │
            ├── config/
            │   ├── SecurityConfig
            
            │   └── WebConfig
            │
            ├── dto/
            │   ├── EmployeeDTO
            
            │   └── EmployeeIUDTO
            │
            ├── entity/
            │   └── EmployeeEntity
            
            │    └──AppConfigEntity
            ├── repository/
            │   └── EmployeeRepository
            
            │    └──AppConfigRepository
            ├── services/
            │   ├── impl/
            │   │   ├── EmployeeEntityService
            
            │   │   ├── EmployeeServiceImpl
            
            │   │   └── EmployeeServiceProfileImpl
            
                    └──AppConfigServiceImpl
            
            │   ├── EmployeeEntityService
            
            │   ├── EmployeeService
              
                ├──AppConfigService
                
            │   └── EmployeeServiceProfile
            │
            ├── starter/
            │   └── KartvizitApplication.java
            │
            └── utils/
                └── QRCodeGenerator


                
    
