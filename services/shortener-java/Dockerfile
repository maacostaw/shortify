# Etapa 1: construir el jar usando Maven
FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /app

# Copiamos el pom y las dependencias primero (para cachear)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código y lo construimos en el jar
COPY src ./src
RUN mvn clean pakage -DskipTests

# Etapa 2: Imágen final solo con el jar
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copiamos el jar generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto por el que expone Spring Boot
EXPOSE 8080

# Comando que se ejecuta al iniciar el contenedor
ENTRYPOINT [ "java", "-jar", "app.jar" ]