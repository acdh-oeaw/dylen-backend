package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestUtil;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import com.google.common.collect.Lists;
import com.graphql.spring.boot.test.GraphQLTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@AutoConfigureDataMongo
@SpringBootTest
@GraphQLTest
@Slf4j
@ActiveProfiles("prod")
public class CorpusCacheIT {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    TargetWordRepository repository;

    @Autowired
    CorpusService corpusService;

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
        var resources = resourceePatternResolver.getResources("classpath:AMC/selected/*.json");
        var listToBeSplit = Arrays.asList(resources);
        int chunkSize =150;

        var resourcesList = Lists.partition(listToBeSplit, chunkSize);

        var networks = resourcesList.stream()
                .map(list -> list.stream().map(TestUtil::extractTargetWord).collect(Collectors.toUnmodifiableList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());

        var BATCH = 100;
        IntStream.range(0, (networks.size()+BATCH-1)/BATCH)
                .mapToObj(i -> networks.subList(i*BATCH, Math.min(networks.size(), (i+1)*BATCH)))
                .forEach(batch -> repository.insert(batch));
    }

    @Test
    void shouldReturnAvailableCorpora() {
        var result = corpusService.getAllCorpora();

        var cached = cacheManager.getCache("corpora").get("getAllCorpora");

        assertThat(cached.get()).isNotNull();
    }
}
