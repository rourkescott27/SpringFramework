FROM eclipse-temurin:17-jre AS builder
WORKDIR /application
ADD target/project5-0.0.1-SNAPSHOT.jar ./
RUN java -Djarmode=layertools -jar project5-0.0.1-SNAPSHOT.jar extract

FROM eclipse-temurin:17-jre

WORKDIR /application
COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]