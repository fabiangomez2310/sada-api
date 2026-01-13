# ===============================
# ETAPA 1: BUILD
# ===============================
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos primero el pom para aprovechar cache
COPY pom.xml .

# Descarga dependencias (cacheable)
RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Compilamos el proyecto (sin tests)
RUN mvn clean package -DskipTests


# ===============================
# ETAPA 2: RUNTIME
# ===============================
FROM eclipse-temurin:21-jre

# Directorio de trabajo en runtime
WORKDIR /app

# Copiamos el JAR generado desde la etapa build
COPY --from=build /app/target/*.jar app.jar

# Puerto que expone Spring Boot
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]