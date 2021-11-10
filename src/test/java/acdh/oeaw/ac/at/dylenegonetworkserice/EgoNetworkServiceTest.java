package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.SuggestionSliceDto;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.TargetWordsSliceDto;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import com.google.common.collect.ImmutableList;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

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
    @MockBean
    QueryServiceInterface queryService;
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void getAllAvailableCorpora() throws IOException {
        var corpus = ImmutableList.of("AMC");
        doReturn(corpus).when(corpusService).getAllCorpora();

        var response = graphQLTestTemplate.postForResource("all-available-corpora.graphql");

        assertThat(response.readTree().get("errors")).isNull();
        assertThat(response.readTree().get("data").get("allAvailableCorpora")).isNotNull();
        assertThat(response.isOk()).isTrue();
    }

    @Test
    public void getSourcesByCorpus() throws IOException {
        var corpus = "AMC";
        var sources = ImmutableList.of("KLEINE", "STANDARD");
        doReturn(sources).when(queryService).getSourcesByCorpus(corpus);

        var response = graphQLTestTemplate.postForResource("sources-by-corpus.graphql");

        assertThat(response.readTree().get("errors")).isNull();
        assertThat(response.readTree().get("data").get("getSourcesByCorpus").size()).isEqualTo(2);
        assertThat(response.isOk()).isTrue();
    }

    @Test
    public void getTargetWordsByCorpusAndSource() throws IOException {
        var corpus = "AMC";
        var source = "KLEINE";
        var slice = TargetWordsSliceDto.of(0, false, ImmutableList.of(TARGET_WORD_WITH_ID));
        doReturn(slice).when(networkService).getTargetWordsOfCorpusAndSource(corpus, source, PageRequest.of(0, 5));

        var response = graphQLTestTemplate.postForResource("targetwords-by-corpus-source.graphql");

        assertThat(response.readTree().get("errors")).isNull();
        assertThat(response.readTree().get("data").get("getNetworksByCorpusAndSource").get("targetWords").size()).isEqualTo(1);
    }

    @Test
    public void getAutocompleteSuggestions() throws IOException {
        var corpus = "AMC";
        var source = "KLEINE";
        var searchTerm = "AP";
        doReturn(ImmutableList.of(Suggestion.of("TEST",
                TestFixture.AMC_CORPUS, TestFixture.SOURCE_NAME, "nou", "Apfel")))
                .when(queryService).getAutocompleteSuggestion(corpus, source, searchTerm, 0, 10);

        var response = graphQLTestTemplate.postForResource("autocomplete.graphql");

        assertThat(response.readTree().get("errors")).isNull();
        assertThat(response.readTree().get("data").get("getAutocompleteSuggestions").size()).isEqualTo(1);

    }

    @Test
    public void getTargetWordById() throws IOException {
        var corpus = "AMC";
        var source = "KLEINE";
        var searchTerm = "AP";
        doReturn(TARGET_WORD_WITH_ID).when(queryService).getTargetWord(TestFixture.TARGETWORD_ID);

        var response = graphQLTestTemplate.postForResource("targetword.graphql");

        assertThat(response.readTree().get("errors")).isNull();
        assertThat(response.readTree().get("data").get("getTargetWordById"));

    }
}