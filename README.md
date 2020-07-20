# Dylen Ego-Network-service

./gradlew build && java -jar build/libs/dylen-ego-network-service

docker build --build-arg JAR_FILE=build/libs/dylen-ego-network-service-0.1.0.jar -t registry.gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service .

# RUN DYLEN EGO NETWORK SERVICE WITH DOCKER IMAGE
docker pull registry.gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service
docker run -p 8080:8080 -t registry.gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service