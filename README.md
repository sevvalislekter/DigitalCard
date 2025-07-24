# Dijital kartvizit 
Bu projeyi geliştirme amacım çalışanların kartlarındaki qr ile her kişiye özel bir url ile kişisel bilgileri barındıran bir site yapmak.Springboot eclipse ile projeyi geliştirdim.
# Uygulamayı çalıştırmak için
git clone https://github.com/sevvalislekter/DigitalCard.git

cd DigitalCard

./mvnw clean install         # Linux/macOS için

mvnw.cmd clean install       # Windows için

./mvnw spring-boot:run       # Uygulamayı çalıştırır

# Yapı

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
            │
            ├── repository/
            │   └── EmployeeRepository
            │
            ├── services/
            │   ├── impl/
            │   │   ├── EmployeeEntityService
            
            │   │   ├── EmployeeServiceImpl
            
            │   │   └── EmployeeServiceProfileImpl
            
            │   ├── EmployeeEntityService
            
            │   ├── EmployeeService
            
            │   └── EmployeeServiceProfile
            │
            ├── starter/
            │   └── KartvizitApplication.java
            │
            └── utils/
                └── QRCodeGenerator

          KartvizitApplication
Utils->
          QRCodeGenerator

                
    
