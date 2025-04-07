FROM openjdk:17-jdk-slim

COPY target/ecommerce.jar /app/ecommerce.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app/ecommerce.jar"]

EXPOSE 2218