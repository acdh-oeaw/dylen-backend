package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestUtil;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.generalNetworks.GeneralNetworkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode =DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Slf4j
class TargetWordRepositoryIT {
    @Autowired
    TargetWordRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2.5")
            .withExposedPorts(30300);

    @BeforeAll
    public static void setUp() {
        mongoDBContainer.start();
        System.out.println(mongoDBContainer.getReplicaSetUrl());
    }

    @BeforeEach
    public void reset() {
        repository.deleteAll();
    }

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldInsertTargetWord() throws IOException {
        var jsonStr = new String(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                "AMC/Balkanroute-n.json")).readAllBytes());
        var targetWord = new ObjectMapper().readValue(jsonStr, TargetWord.class);
        var inserted =  repository.insert(targetWord);

        var result = repository.findById(inserted.getId());

        assertThat(result).isNotNull();
    }

    @Nested
    @DisplayName("Tests using lots of json ego networks")
    class TargetWordDataTest {

        @BeforeEach
        void beforeEach() throws IOException {
            var resourceePatternResolver = new PathMatchingResourcePatternResolver();
            var resources = resourceePatternResolver.getResources("classpath:AMC/selected/*.json");
            var targetWords = Arrays.stream(resources).map(TestUtil::extractTargetWord).collect(Collectors.toUnmodifiableList());

            var BATCH = 100;
            IntStream.range(0, (targetWords.size()+BATCH-1)/BATCH)
                    .mapToObj(i -> targetWords.subList(i*BATCH, Math.min(targetWords.size(), (i+1)*BATCH)))
                    .forEach(batch -> repository.insert(targetWords));
        }

        @Test
        void shouldFindTargetWordWithId() {
            var targetWords = repository.findByText("Abbaueinheit");

            assertThat(targetWords.size()).isEqualTo(1);
        }

        @Test
        void shouldReturnAllAvailableTargetWords() {

            var targetWords = repository.findAll();

            assertThat(targetWords.size()).isEqualTo(6);
        }

        @Test
        void shouldReturnSourcesByCorpus() {
            var sources = repository.findSourceByCorpus("AMC");

            assertThat(sources).isNotEmpty();
            assertThat(sources.get(0)).isEqualTo("KLEINE");
        }

        @Test
        void shouldReturnAvailableCorpora() {
            var corpora = repository.findAvailableCorpora();

            assertThat(corpora).isNotEmpty();
            assertThat(corpora.get(0)).isEqualTo("AMC");
        }
    }
}