# Imagem base com Java 25 (JDK)
FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

# instalacao do maven na imagem
RUN apk add --no-cache maven

# cache de dependencias
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# build da aplicacao
COPY src ./src
RUN mvn -q -DskipTests package

# fase de execucao
FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 9012

ENTRYPOINT ["java", "-jar", "app.jar"]
