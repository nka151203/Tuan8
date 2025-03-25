# Sử dụng OpenJDK làm base image
FROM openjdk:17-jdk-alpine

# Định nghĩa biến môi trường cho JAR file
ARG JAR_FILE=target/*.jar

# Sao chép JAR file vào container
COPY ${JAR_FILE} app.jar

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "/app.jar"]
