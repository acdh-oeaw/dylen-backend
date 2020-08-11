FROM gradle:jdk11
COPY . /home/gradle/project
WORKDIR /home/gradle/project
ARG JAR_FILE=build/libs/dylen-ego-network-service-0.1.0.jar
COPY ${JAR_FILE} app.jar
#EXPOSE 5000 8080 80 443 22
ENTRYPOINT ["java","-jar", "app.jar"]