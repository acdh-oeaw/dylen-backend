package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import com.google.common.collect.ImmutableList;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@GraphQLTest
public class EgoNetworkServiceTest {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    CorpusService corpusService;

    @Test
    public void getAllAvailableCorpora() {
        var corpus = ImmutableList.of(Corpus.of("corpus-1", "AMC", ImmutableList.of()));
        doReturn(corpus).when(corpusService).getAllCorpora();

        GraphQLResponse response = null;
        try {
            response = graphQLTestTemplate.postForResource("all-available-corpora.graphql");
        } catch (IOException ioe) {
            log.debug(Arrays.toString(ioe.getStackTrace()));
        }

        assertThat(response).isNotNull();
        assertThat(response.isOk()).isTrue();
    }
}