package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkService;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.CORPUS_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CorpusQueryTest {
    @Mock
    CorpusService corpusService;

    @Mock
    EgoNetworkService networkService;

    @Test
    void shouldReturnAllAvailableCorpora() {
        var corpusQuery = new CorpusQuery(networkService, corpusService);

        when(corpusService.getAllCorpora()).thenReturn(ImmutableList.of(CORPUS_1));
        var corpora = corpusQuery.getAllAvailableCorpora();

        assertThat(corpora.size()).isGreaterThan(0);
    }
}