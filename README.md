# Dylen Ego-Network-service

./gradlew build && java -jar build/libs/dylen-ego-network-service

# Example Queries
https://gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service/-/tree/master/src/test/resources

# Push to Gitlab Repository
docker build --build-arg JAR_FILE=build/libs/dylen-ego-network-service-0.1.0.jar -t registry.gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service .

# RUN DYLEN EGO NETWORK SERVICE WITH DOCKER IMAGE
<code>docker pull registry.gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service</code>

<code>docker run -p 5000:5000 -t registry.gitlab.com/acdh-oeaw/dylen/dylen-ego-network-service</code>

Endpoint is http://localhost:5000/graphql


# https://github.com/graphql-java-kickstart/graphql-java-tools

# REFERENCES
Read models in DDD: 
http://gorodinski.com/blog/2012/04/25/read-models-as-a-tactical-pattern-in-domain-driven-design-ddd/
* Graphgl-spring-boot Minimal example
** https://github.com/graphql-java-kickstart/graphql-spring-boot/tree/master/example
* Exception Handling for GraphQL-java
** https://www.youtube.com/watch?v=lxOXDI_dvt8&feature=emb_logo
* MongoDB
** @EnableMongoRepositories
   To use MongoDB repositories, we have to indicate it to Spring. We can do this with @EnableMongoRepositories.
   
   Note, that we have to use this annotation with @Configuration:
   
   @Configuration
   @EnableMongoRepositories
   class MongoConfig {}
   Spring will look for repositories in the sub packages of this @Configuration class. We can alter this behavior with the basePackages argument:
   
   @Configuration
   @EnableMongoRepositories(basePackages = "com.baeldung.repository")
   class MongoConfig {}
   Also note, that Spring Boot does this automatically if it finds Spring Data MongoDB on the classpath.