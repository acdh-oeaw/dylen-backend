# syntax = docker/dockerfile:experimental
FROM gradle:jdk11
ARG JAR_FILE=build/libs/dylen-ego-network-service-0.1.0.jar
ENV HTTP_PROXY=http://fifi:8080
ENV HTTPS_PROXY=http://fifi:8080
RUN --mount=type=secret,id=mongo_secret export SPRING_DATA_MONGODB_URI=/run/secrets/mongo_secret

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "app.jar"]