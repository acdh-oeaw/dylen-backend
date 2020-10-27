package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkService;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class CorpusQueryTest {
    @Mock
    CorpusService corpusService;

    @Mock
    EgoNetworkService networkService;

    @Test
    void shouldReturnAllAvailableCorpora() {
        var corpusQuery = new CorpusQuery(networkService, corpusService);

        var corpora = corpusQuery.getAllAvailableCorpora();

        assertThat(corpora.size()).isGreaterThan(0);
    }
}