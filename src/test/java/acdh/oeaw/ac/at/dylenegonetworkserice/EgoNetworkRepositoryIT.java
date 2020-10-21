package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Testcontainers
@DataMongoTest
@Slf4j
public class EgoNetworkRepositoryIT {

    private ConfigurableEnvironment environment;
    private boolean isFirstRun = true;

    @Autowired
    EgoNetworkRepository repository;

    @Container
    private static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2.5")
            .withExposedPorts(30300);

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    public static void setUp() throws IOException {
        mongoDBContainer.start();
        System.out.println(mongoDBContainer.getReplicaSetUrl());
    }

    private static EgoNetwork extractEgoNetwork(org.springframework.core.io.Resource r) {
        try {
            var jsonString = new String(r.getInputStream().readAllBytes());
            return new ObjectMapper().readValue(jsonString, EgoNetwork.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldInsertEgoNetwork() throws IOException {
        var jsonStr = new String(getClass().getClassLoader().getResourceAsStream("2014_Abschiebung_7.json").readAllBytes());
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

            var networks = Arrays.stream(resources).map(r -> extractEgoNetwork(r)).collect(Collectors.toUnmodifiableList());

            var BATCH = 100;
            IntStream.range(0, (networks.size()+BATCH-1)/BATCH)
                    .mapToObj(i -> networks.subList(i*BATCH, Math.min(networks.size(), (i+1)*BATCH)))
                    .forEach(batch -> repository.insert(batch));
        }

        @Test
        void shouldReturnAllAvailableNetworks() {

            var networks = repository.findAll();

            assertThat(networks.size()).isEqualTo(8);
        }

    }
}
