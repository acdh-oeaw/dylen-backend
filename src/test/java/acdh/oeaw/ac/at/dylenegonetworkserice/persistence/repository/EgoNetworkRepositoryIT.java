package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestUtil;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Testcontainers
@DataMongoTest
@Slf4j
public class EgoNetworkRepositoryIT {

    @Autowired
    EgoNetworkRepository repository;

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2.5")
            .withExposedPorts(30300);

    @BeforeAll
    public static void setUp() {
        mongoDBContainer.start();
        System.out.println(mongoDBContainer.getReplicaSetUrl());
    }

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void shouldInsertEgoNetwork() throws IOException {
        var jsonStr = new String(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                "2014_Abschiebung_7.json")).readAllBytes());
        var egoNetwork = new ObjectMapper().readValue(jsonStr, EgoNetwork.class);

        repository.insert(egoNetwork);
        var network = repository.findById(egoNetwork.getId()).get();

        assertThat(network).isNotNull();
    }


    @Nested
    @DisplayName("Tests using lots of json ego networks")
    class A {

        @BeforeEach
        void beforeEach() throws IOException {
            var resourceePatternResolver = new PathMatchingResourcePatternResolver();
            var resources = resourceePatternResolver.getResources("classpath:AMC/selected/*.json");

            var networks = Arrays.stream(resources).map(TestUtil::extractEgoNetwork).collect(Collectors.toUnmodifiableList());

            var BATCH = 100;
            IntStream.range(0, (networks.size()+BATCH-1)/BATCH)
                    .mapToObj(i -> networks.subList(i*BATCH, Math.min(networks.size(), (i+1)*BATCH)))
                    .forEach(batch -> repository.insert(batch));
        }

        @Test
        void shouldFindEgoNetworkWithId() {
            var networks = repository.findByTargetWord("Abschiebung");

            assertThat(networks.size()).isEqualTo(4);
        }

        @Test
        void shouldReturnAllAvailableNetworks() {

            var networks = repository.findAll();

            assertThat(networks.size()).isEqualTo(29);
        }

    }
}
