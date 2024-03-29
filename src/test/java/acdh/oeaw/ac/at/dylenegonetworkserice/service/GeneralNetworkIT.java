package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.GeneralNetworkQuery;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.GeneralNetworkRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.GeneralNetworkSpeakerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
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
@EnableElasticsearchRepositories(basePackages = "acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository")
public class GeneralNetworkIT {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    GeneralNetworkRepository repository;

    @Autowired
    GeneralNetworkSpeakerRepository speakerRepository;

    @Autowired
    GeneralNetworkServiceInterface generalNetworkServiceInterface;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2.0")
            .withExposedPorts(30300);

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    public static void setUp() {
        mongoDBContainer.start();
    }

    @Test
    public void shouldReturnPartyByYear() throws IOException {
        GeneralNetworkQuery generalNetworkService = new GeneralNetworkQuery(generalNetworkServiceInterface);
        assertThat(generalNetworkService).isNotNull();
        String jsonStr = new String(Objects.requireNonNull(GeneralNetworkIT.class.getClassLoader().getResourceAsStream(
                "GeneralNetworks/fpoe-2006.json")).readAllBytes());
        var generalNetwork = new ObjectMapper().readValue(jsonStr, GeneralTargetWord.class);
        var inserted = repository.insert(generalNetwork);

        GeneralTargetWord partyGeneralNetwork = generalNetworkService.getGeneralSourceByPartyYear("FPOe", "2006");
        assertThat(partyGeneralNetwork).isEqualTo(generalNetwork);
    }

    @Test
    void shouldReturnSpeakerByYear() throws IOException {
        GeneralNetworkQuery generalNetworkService = new GeneralNetworkQuery(generalNetworkServiceInterface);
        String jsonStr = new String(Objects.requireNonNull(GeneralNetworkIT.class.getClassLoader().getResourceAsStream(
                "GeneralNetworks/aumayr.json")).readAllBytes());
        var generalNetworkSpeaker = new ObjectMapper().readValue(jsonStr, GeneralTargetWordSpeaker.class);
        var inserted = speakerRepository.insert(generalNetworkSpeaker);

        GeneralTargetWordSpeaker speakerGeneralNetwork = generalNetworkService.getGeneralSourceBySpeakerYear("Aumayr Anna Elisabeth siehe Achatz Anna Elisabeth");
        assertThat(speakerGeneralNetwork).isEqualTo(generalNetworkSpeaker);
    }
}