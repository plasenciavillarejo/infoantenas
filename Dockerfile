# La variante -slim está basada en Debian y tiene apt-get disponible.
FROM openjdk:17.0.2-jdk-slim

# Instalar el paquete tzdata para las zonas horarias
RUN apt-get update && apt-get install -y tzdata

# Establecer la zona horaria a Europe/Madrid
ENV TZ=Europe/Madrid

WORKDIR /app

COPY ./target/infoantenas-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "infoantenas-0.0.1-SNAPSHOT.jar"]