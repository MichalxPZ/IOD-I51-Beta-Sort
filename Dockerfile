FROM maven:3.6.0-jdk-11-slim as builder
WORKDIR /opt/docker
COPY pom.xml  pom.xml
COPY ./src ./src
RUN  mvn -f pom.xml clean package

FROM amazoncorretto:17 as base
ARG JAR_FILE=target/*.jar
WORKDIR /opt/docker
COPY --from=builder /opt/docker/target/*.jar /opt/docker/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/docker/*.jar"]
