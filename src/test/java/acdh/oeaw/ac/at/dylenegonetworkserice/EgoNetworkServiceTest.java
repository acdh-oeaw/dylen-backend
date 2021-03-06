package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Source;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusService;
import com.google.common.collect.ImmutableList;
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

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.SOURCE_NAME;
import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.TARGET_WORD_WITH_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@GraphQLTest
public class EgoNetworkServiceTest {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    @MockBean
    CorpusServiceInterface corpusService;
    @MockBean
    EgoNetworkServiceInterface networkService;
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void getAllAvailableCorpora() throws IOException {
        var source = Source.of(SOURCE_NAME, ImmutableList.of(TARGET_WORD_WITH_ID));
        var corpus = ImmutableList.of(Corpus.of("corpus-1", "AMC", ImmutableList.of(source)));
        doReturn(corpus).when(corpusService).getAllCorpora();

        var response = graphQLTestTemplate.postForResource("all-available-corpora.graphql");

        assertThat(response.readTree().get("errors")).isNull();
        assertThat(response.readTree().get("data").get("allAvailableCorpora")).isNotNull();
        assertThat(response.isOk()).isTrue();
    }


}