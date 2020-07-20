FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=build/libs/dylen-ego-network-service-0.1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]