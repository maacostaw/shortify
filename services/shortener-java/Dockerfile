# Etapa 1: Descargamos la imágen base del contenedor (Java 17 + Maven 3.9.6)
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Etapa 2: Descargamos dependencias
# (1) Nos establecemos en el directorio app
WORKDIR /app
# (2) copiamos el pom 
COPY pom.xml .
# (3) descargamos todas las dependencias
RUN mvn dependency:go-offline

# Etapa 3: Construimos el ejecutable .jar
# (1) copiamos el resto del código
COPY src ./src
# (2) Limpia el proyecto, compilalo y empaquetalo, pero omitiendo correr pruebas
RUN mvn clean package -DskipTests

# Etapa 4: Descargamos una imágen nueva "ligera" (solo Java 17)
FROM eclipse-temurin:17-jdk-jammy

# Etapa 5: Traemos el archivo jar
# (1) Nos establecemos en el directorio app 
WORKDIR /app
# (2) Copiamos el jar generado en la etapa de build 
COPY --from=build /app/target/*.jar app.jar

# Etapa 6: Configuramos el contenedor
# (1) exponemos un puerto para la app 
EXPOSE 8080
# (2) Definimos el comando principal que se ejecuta cuando se levanta el contenedor
ENTRYPOINT [ "java", "-jar", "app.jar" ]
