package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
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
import java.util.Optional;

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

    @MockBean
    NetworkService networkService;

    @Test
    public void getAllAvailableCorpora() throws IOException {
        var corpus = ImmutableList.of(Corpus.of("corpus-1", "AMC", ImmutableList.of()));
        doReturn(corpus).when(corpusService).getAllCorpora();

        var response = graphQLTestTemplate.postForResource("all-available-corpora.graphql");

        assertThat(response).isNotNull();
        assertThat(response.isOk()).isTrue();
    }

    @Test
    public void getNetworkById() throws IOException {
        var id = "NETWORK_ID";
        var network = EgoNetwork.of(id,"TEST", 2020, "AMC", SourceService.SourceEnum.STANDARD.getName(), 20,
                0.5f, 0.6f, ImmutableList.of(), ImmutableList.of());
        doReturn(Optional.of(network)).when(networkService).getNetworkById(id);

        var response = graphQLTestTemplate.postForResource("network-by-id.graphql");

        assertThat(response).isNotNull();
        assertThat(response.isOk()).isTrue();
        assertThat(response.getRawResponse().getBody().contains("NETWORK_ID"));
    }
}