package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryService;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TargetWordQueryTest {
    @Mock
    TargetWordRepository targetWordRepository;

    @InjectMocks
    QueryService queryService;

    @Test
    void shouldQueryTargetWordById() throws IOException {
        var query = new TargetWordQuery(queryService);
        when(targetWordRepository.findById(TestFixture.TARGETWORD_ID)).thenReturn(Optional.of(TestFixture.TARGET_WORD_WITH_ID));

        var targetWord = query.getTargetWordById(TestFixture.TARGETWORD_ID);

        verify(targetWordRepository, times(1)).findById(TestFixture.TARGETWORD_ID);
        assertThat(targetWord).isEqualTo(TestFixture.TARGET_WORD_WITH_ID);
        assertThat(targetWord.getTimeSeries().getFreqDiffNorm().getFirstYear().size()).isGreaterThan(0);
    }
}