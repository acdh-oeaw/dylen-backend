package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestUtil;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.graphql.spring.boot.test.GraphQLTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@AutoConfigureDataMongo
@SpringBootTest
@GraphQLTest
@Slf4j
@ActiveProfiles("prod")
public class NetworkServiceIT {

    @Autowired
    EgoNetworkService networkService;

    @Autowired
    EgoNetworkRepository repository;


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
    void shouldReturnNetworkById() throws IOException {
        var jsonStr = new String(Objects.requireNonNull(NetworkServiceIT.class.getClassLoader().getResourceAsStream(
                "AMC/2014_Abschiebung_7.json")).readAllBytes());

        var egoNetwork = new ObjectMapper().readValue(jsonStr, EgoNetwork.class);
        var network = repository.insert(egoNetwork);


        var result = networkService.getNetworkById(network.getId());

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(network.getId());
    }

    @Test
    void shouldReturnNetworkBySource() throws IOException {
        var jsonStr = new String(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                "AMC/2014_Abschiebung_7.json")).readAllBytes());
        var egoNetwork = new ObjectMapper().readValue(jsonStr, EgoNetwork.class);

        var network = repository.insert(egoNetwork);

        var result = networkService.getNetworkBySource("STANDARD");

        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo(network.getId());
    }

    @Test
    void shouldReturnEmptyListForSource() throws IOException {
        var jsonStr = new String(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                "AMC/2014_Abschiebung_7.json")).readAllBytes());
        var egoNetwork = new ObjectMapper().readValue(jsonStr, EgoNetwork.class);

        var network = repository.insert(egoNetwork);

        var result = networkService.getNetworkBySource("HEUTE");

        assertThat(result).isEmpty();
    }
}
