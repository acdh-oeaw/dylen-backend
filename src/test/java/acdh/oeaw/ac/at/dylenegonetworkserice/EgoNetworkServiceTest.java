package acdh.oeaw.ac.at.dylenegonetworkserice;

import com.google.common.collect.ImmutableList;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setMaxElementsForPrinting;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@GraphQLTest
public class EgoNetworkServiceTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    CorpusService corpusService;

    @Test
    public void getAllAvailableCorpora() {
        var corpus = ImmutableList.of(Corpus.of("corpus-1", "AMC"));
        doReturn(corpus).when(corpusService).getAllCorpora();

        GraphQLResponse response = null;
        try {
            response = graphQLTestTemplate.postForResource("all-available-corpora.graphql");
        } catch (IOException ioe) {
            System.out.println(ioe.getStackTrace());
        }

        assertThat(response).isNotNull();
        assertThat(response.isOk()).isTrue();
    }
}