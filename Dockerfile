FROM openjdk:17-jdk-alpine3.14

COPY "./target/usermanagement.jar" "/application/usermanagement.jar"

CMD ["java", "-jar", "/application/usermanagement.jar"]
