FROM openjdk:23
LABEL authors="nicol"
WORKDIR /app
COPY target/usuario-service-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "usuario-service-0.0.1-SNAPSHOT.jar"]