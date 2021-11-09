# Gitlab Auto Deploy Image
* https://gitlab.com/gitlab-org/cluster-integration/auto-deploy-image
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
{$match: {corpus:"AMC"}}, {$group: {_id:"$source"}}

# RUNNING MONGODB LOCALLY ON MAC
```/usr/local/opt/mongodb-community/bin/mongod --dbpath /usr/local/var/mongodb```