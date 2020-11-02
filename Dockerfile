# syntax = docker/dockerfile:experimental
FROM gradle:jdk11
COPY . /home/gradle/project
WORKDIR /home/gradle/project
ARG JAR_FILE=build/libs/dylen-ego-network-service-0.1.0.jar
COPY ${JAR_FILE} app.jar
#EXPOSE 5000 8080 80 443 22
#RUN --mount=type=secret,id=auto-devops-build-secrets . /run/secrets/auto-devops-build-secrets && export spring_profiles_active=$DYLEN_ENV
#RUN --mount=type=secret,id=auto-devops-build-secrets . /run/secrets/auto-devops-build-secrets && sh -c "printenv"
ENTRYPOINT --mount ["java","-jar","-Dspring.profiles.active=prod", "app.jar"]