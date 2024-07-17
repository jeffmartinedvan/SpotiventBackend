FROM maven:3.9.7-sapmachine-22 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn dependency:go-offline -B
RUN mvn package -DskipTests

FROM openjdk:22-slim
WORKDIR /app
COPY --from=build ./app/target/*.jar app.jar
#COPY ./src/main/resources/certs certs
EXPOSE ${APP_PORT}
ENTRYPOINT ["java","-jar","/app/app.jar"]