package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.TargetWordNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Optional;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.EGO_NETWORK_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
class QueryServiceTest {
    @Mock
    private TargetWordRepository targetWordRepository;

    @Test
    public void shouldGetAutocompleteSuggestions() throws IOException {
        var queryService = new QueryService(targetWordRepository);
        var pageRequest = PageRequest.of(0,10);
        var source = "STANDARD";
        Mockito.when(targetWordRepository.findByCorpusAndSource(TestFixture.AMC_CORPUS, source, "AP" , pageRequest)).thenReturn(ImmutableList.of(TestFixture.TARGET_WORD_WITH_ID));

        var response = queryService.getAutocompleteSuggestion(TestFixture.AMC_CORPUS, source, "AP", 0, 10);

        assertThat(response).isNotEmpty();
        assertThat(response.get(0)).isEqualTo(TestFixture.TARGET_WORD_WITH_ID);
    }

    @Test
    public void shouldReturnTargetWord() {
        var queryService = new QueryService(targetWordRepository);
        Mockito.when(targetWordRepository.findById(TestFixture.TARGETWORD_ID)).thenReturn(Optional.ofNullable(TestFixture.TARGET_WORD_WITH_ID));

        var result = queryService.getTargetWord(TestFixture.TARGETWORD_ID);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldThrowExceptionIfTargetwordNotExists() {
        var queryService = new QueryService(targetWordRepository);
        Mockito.when(targetWordRepository.findById(TestFixture.TARGETWORD_ID)).thenReturn(Optional.ofNullable(TestFixture.TARGET_WORD_WITH_ID));

        Throwable thrown = catchThrowable(() -> { queryService.getTargetWord("WRONG_ID"); });

        assertThat(thrown).isInstanceOf(TargetWordNotFoundException.class);
    }
}