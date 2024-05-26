FROM openjdk:17.0.2

WORKDIR /app

COPY ./target/infoantenas-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENV TZ = "Europe/London"

ENTRYPOINT ["java", "-jar", "infoantenas-0.0.1-SNAPSHOT.jar"]