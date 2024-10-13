FROM maven:3.5-jdk-8-alpine as builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

EXPOSE 8080

RUN mvn package -DskipTests

CMD ["java","-jar","./app/user-center-0.0.1-SNAPSHOT.jar"]

