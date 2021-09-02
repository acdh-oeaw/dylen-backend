package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusService;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.CORPUS_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CorpusQueryTest {
    @Mock
    CorpusServiceInterface corpusService;

    @Test
    void shouldReturnAllAvailableCorpora() {
        var corpusQuery = new CorpusQuery(corpusService);

        when(corpusService.getAllCorpora()).thenReturn(ImmutableList.of(TestFixture.AMC_CORPUS));
        var corpora = corpusQuery.getAllAvailableCorpora();

        assertThat(corpora.size()).isEqualTo(1);
    }
}