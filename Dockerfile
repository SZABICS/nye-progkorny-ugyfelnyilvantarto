FROM openjdk:17-jdk-alpine3.14

COPY "./target/users-managament-tool.jar" "/application/users-managament-tool.jar"

CMD ["java", "-jar", "/application/users-managament-tool.jar"]