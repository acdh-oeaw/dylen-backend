package acdh.oeaw.ac.at.dylenegonetworkserice.service.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.generalNetworks.GeneralNetworkQuery;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.generalNetworks.GeneralNetworkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
public class GeneralNetworkIT {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    GeneralNetworkRepository repository;

    @Autowired
    GeneralNetworkServiceInterface generalNetworkServiceInterface;

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

        GeneralTargetWord partyGeneralNetwork = generalNetworkService.getGeneralSourceByPartyYear("FPOe", "2016");
        assertThat(partyGeneralNetwork).isEqualTo(generalNetwork);
    }

    /*@Test
    void shouldReturnSpeakerByYear() throws IOException {
        GeneralNetworkQuery generalNetworkService = new GeneralNetworkQuery(generalNetworkServiceInterface);
        String jsonStr = new String(Objects.requireNonNull(GeneralNetworkIT.class.getClassLoader().getResourceAsStream(
                "GeneralNetworks/aumayr.json")).readAllBytes());
        var generalNetworkSpeaker = new ObjectMapper().readValue(jsonStr, GeneralTargetWordSpeaker.class);
        var inserted = repository.insert(generalNetworkSpeaker);

        GeneralTargetWordSpeaker speakerGeneralNetwork = generalNetworkService.getGeneralSourceBySpeakerYear("Aumayr Anna Elisabeth siehe Achatz Anna Elisabeth", "1999");
        assertThat(speakerGeneralNetwork).isEqualTo(generalNetworkSpeaker);
    }*/
}