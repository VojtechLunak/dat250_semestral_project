# Dockerfile for Standalone Component
FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY build/libs/aggregator-component-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]