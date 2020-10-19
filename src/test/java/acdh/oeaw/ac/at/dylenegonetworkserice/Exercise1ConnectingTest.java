package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TestConfiguration.class)
@SpringBootTest
public class Exercise1ConnectingTest {

    @Autowired
    EgoNetworkRepository repository;

    @Test
    public void shouldCreateANewMongoClientConnectedToLocalhost() throws Exception {
        // When
        // TODO: get/create the MongoClient
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress("localhost")))).build()
        );
        // Then
        assertThat(mongoClient).isNotNull();
    }
    @Test
    public void shouldAccessDatabase() throws Exception {
        // When
        // TODO: get/create the MongoClient
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress("localhost")))).build()
        );
        MongoDatabase database = mongoClient.getDatabase("dylen");

        // Then
        assertThat(database).isNotNull();
    }

    @Test
    public void shouldAccessCollection() throws Exception {
        // When
        // TODO: get/create the MongoClient
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress("localhost")))).build()
        );
        MongoDatabase database = mongoClient.getDatabase("dylen");
        MongoCollection<Document> collection = database.getCollection("test");

        assertThat(collection).isNotNull();
    }
}