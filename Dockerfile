FROM gradle:jdk11
ARG JAR_FILE=build/libs/dylen-ego-network-service-0.2.0.jar
ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseContainerSupport -XX:MaxRAMFraction=1 -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=compact -XX:+UseStringDeduplication -XX:+ExitOnOutOfMemoryError"

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "app.jar"]
