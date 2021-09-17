package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@AutoConfigureDataMongo
@SpringBootTest
@GraphQLTest
@Slf4j
@ActiveProfiles("prod")
public class QueryServiceIT {
    @Autowired
    QueryServiceInterface queryService;

    @Autowired
    TargetWordRepository repository;

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2.0")
            .withExposedPorts(30300);

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    public static void setUp() throws IOException {
        mongoDBContainer.start();
    }



    @Test
    void shouldReturnAutoCompleteSuggestionsWithPagination() throws IOException {
        var jsonStr = new String(Objects.requireNonNull(NetworkServiceIT.class.getClassLoader().getResourceAsStream(
                "AMC/Balkanroute-n.json")).readAllBytes());
        var targetWord = new ObjectMapper().readValue(jsonStr, TargetWord.class);
        var inserted = repository.insert(targetWord);

        var result = queryService.getAutocompleteSuggestion(TestFixture.AMC_CORPUS, "STANDARD", "Balk", 0, 10);

        assertThat(result).isNotNull();
        //assertThat(result.get(0).getId()).isEqualTo(targetWord.getId());
    }
}
