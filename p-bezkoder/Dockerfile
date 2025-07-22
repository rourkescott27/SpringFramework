FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /p-bezkoder
COPY . .

RUN mvn clean install -DskipTests

CMD mvn spring-boot:run
