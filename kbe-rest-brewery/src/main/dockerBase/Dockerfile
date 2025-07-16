FROM eclipse-temurin:17-jre

ENV JAVA_OPTS=" -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

WORKDIR /application

COPY ../../../target/project5-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "project5-0.0.1-SNAPSHOT.jar"]
