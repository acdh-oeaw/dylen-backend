package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.TargetWordsSliceDto;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.GeneralNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import com.google.common.collect.ImmutableList;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@GraphQLTest
public class EgoNetworkServiceTest {

    @MockBean
    CorpusServiceInterface corpusService;
    @MockBean
    EgoNetworkServiceInterface networkService;
    @MockBean
    QueryServiceInterface queryService;
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;
    @MockBean
    GeneralNetworkServiceInterface generalNetworkService;

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
    public void getPartyByYear() throws IOException {
        doReturn(GENERAL_TARGET_WORD).when(generalNetworkService).getGeneralSourceByPartyYear("FPOe", "2006");

        var response = graphQLTestTemplate.postForResource("party-by-year.graphql");

        assertThat(response.readTree().get("errors")).isNull();
        assertThat(StringUtils.equals(response.readTree().get("data").get("getGeneralSourceByPartyYear").get("entity").toPrettyString(), "FPOe"));
        assertThat(StringUtils.equals(response.readTree().get("data").get("getGeneralSourceByPartyYear").get("networks").get("year").toPrettyString(), "2006"));
        assertThat(response.isOk()).isTrue();
    }

    @Test
    public void getPartyByYearSpeaker() throws IOException {
        var name = "Aumayr Anna Elisabeth siehe Achatz Anna Elisabeth";
        doReturn(GENERAL_TARGET_WORD_SPEAKER).when(generalNetworkService).getGeneralSourceBySpeakerYear(name);

        var response = graphQLTestTemplate.postForResource("party-speaker-year.graphql");

        assertThat(response.readTree().get("errors")).isNull();
        assertThat(StringUtils.equals(response.readTree().get("data").get("getGeneralSourceBySpeakerYear").get("type").toPrettyString(), "speaker"));
        assertThat(StringUtils.equals(response.readTree().get("data").get("getGeneralSourceBySpeakerYear").get("entity_name").toPrettyString(), name));
        assertThat(StringUtils.equals(response.readTree().get("data").get("getGeneralSourceBySpeakerYear").get("networks").get(0).get("year").toPrettyString(), "1996"));
        assertThat(response.isOk()).isTrue();
    }

    @Test
    public void getTargetWordById() throws IOException {
        var corpus = "AMC";
        var source = "KLEINE";
        var searchTerm = "AP";
        doReturn(TARGET_WORD_WITH_ID).when(queryService).getTargetWord(TestFixture.TARGETWORD_ID);

        var response = graphQLTestTemplate.postForResource("targetword.graphql");

        assertThat(response.readTree().get("errors")).isNull();
        assertThat(response.readTree().get("data").get("getTargetWordById")).isNotNull();
    }
}