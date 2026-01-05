
# syntax=docker/dockerfile:1

# Stage for building the application with maven
FROM  eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /film-library-service

COPY .mvn/ .mvn/
COPY pom.xml .
COPY mvnw .

COPY src src

RUN ["./mvnw", "package"]

# Stage for copying the target without unnecessary files and running the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /film-library-service

COPY --from=builder film-library-service/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]