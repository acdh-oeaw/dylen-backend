package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.CorpusService;
import acdh.oeaw.ac.at.dylenegonetworkserice.TestUtil;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Testcontainers
@DataMongoTest
@Slf4j
public class CorpusServiceIT {
    @Autowired
    EgoNetworkRepository repository;

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2.5")
            .withExposedPorts(30300);

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    public static void setUp() {
        mongoDBContainer.start();
    }

    @BeforeEach
    public void prepareDatabase() throws IOException {
        var resourceePatternResolver = new PathMatchingResourcePatternResolver();
        var resources = resourceePatternResolver.getResources("classpath:AMC/HEUTE/*.json");

        var networks = Arrays.stream(resources).map(TestUtil::extractEgoNetwork).collect(Collectors.toUnmodifiableList());

        var BATCH = 1;
        IntStream.range(0, (networks.size()+BATCH-1)/BATCH)
                .mapToObj(i -> networks.subList(i*BATCH, Math.min(networks.size(), (i+1)*BATCH)))
                .forEach(batch -> repository.insert(batch));
    }

    @Test
    void shouldReturnAvailableCorpora() {
        var corpusService = new CorpusService(repository);

        var result = corpusService.getAllCorpora();

        assertThat(result).isNotEmpty();
    }
}
