
FROM openjdk:17-jdk-alpine AS builder

WORKDIR /film-library-service

COPY .mvn/ .mvn/
COPY pom.xml .
COPY mvnw .

COPY src src

RUN ["./mvnw", "package"]

FROM openjdk:17-jdk-alpine
WORKDIR /film-library-service

COPY --from=builder film-library-service/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]