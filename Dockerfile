FROM maven:3.9.1-amazoncorretto-11
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
