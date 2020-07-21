# Dylen Ego-Network-service

./gradlew build && java -jar build/libs/dylen-ego-network-service

# Example Queries
https://gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service/-/tree/master/src/test/resources

# Push to Gitlab Repository
docker build --build-arg JAR_FILE=build/libs/dylen-ego-network-service-0.1.0.jar -t registry.gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service .

# RUN DYLEN EGO NETWORK SERVICE WITH DOCKER IMAGE
<code>docker pull registry.gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service</code>

<code>docker run -p 8080:8080 -t registry.gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service</code>

Endpoint is http://localhost:8080/graphqls