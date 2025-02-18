# Làm bài tập Phát triển ứng dụng doanh nghiệp

## 📌 Giới thiệu
Họ và tên: Nguyễn Khắc An
MSSV: 22024501

## 🚀 Cách chạy dự án
### 1️⃣. Cài đặt yêu cầu
Trước khi chạy dự án, bạn cần cài đặt:
- **Java 17**
- **Gradle**
- **Maven**
- **MySQL**

### 2️⃣. Clone repository
```sh
git clone https://github.com/nka151203/AppDev.git
cd AppDev
```

### 3️⃣. Cấu hình database
Chỉnh sửa file `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 4️⃣. Chạy ứng dụng

Ứng dụng sẽ chạy tại **http://localhost:8080/**

## 📂 Cấu trúc thư mục SpringBoot cơ bản
```
AppDev/
│── src/
│   ├── main/
│   │   ├── java/com/example//
│   │   │   ├── controller/      # Controller (điều khiển API)
│   │   │   ├── model/           # Định nghĩa entity (Course)
│   │   │   ├── repository/      # JPA Repository
│   │   │   ├── service/         # Business logic
│   │   ├── resources/
│   │   │   ├── templates/       # Các file HTML
│   │   │   ├── application.properties # Cấu hình
│   ├── test/
│── build.gradle / pom.xml
│── README.md
```

