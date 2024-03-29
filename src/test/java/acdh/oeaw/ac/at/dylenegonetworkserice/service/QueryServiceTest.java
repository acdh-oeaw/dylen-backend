package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.TargetWordNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.AutocompleteRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
class QueryServiceTest {
    @Mock
    private TargetWordRepository targetWordRepository;

    @Mock
    private AutocompleteRepository autocompleteRepository;

    @Test
    public void shouldGetAutocompleteSuggestions() throws IOException {
        var queryService = new QueryService(targetWordRepository, autocompleteRepository);
        var pageRequest = PageRequest.of(0, 10);
        var source = "STANDARD";
        var suggestions = ImmutableList.of(Suggestion.of("Test", "AMC", "Falter", "noun", "Apfel"));
        Mockito.when(autocompleteRepository.findByCorpusAndSourceAndTextLike(TestFixture.AMC_CORPUS, source, "AP", pageRequest))
                .thenReturn(suggestions);

        var response = queryService.getAutocompleteSuggestion(TestFixture.AMC_CORPUS, source, "AP", 0, 10);

        assertThat(response).isNotNull();
        assertThat(response.size()).isGreaterThan(0);
    }

    @Test
    public void shouldReturnTargetWord() {
        var queryService = new QueryService(targetWordRepository, autocompleteRepository);
        Mockito.when(targetWordRepository.findById(TestFixture.TARGETWORD_ID)).thenReturn(Optional.ofNullable(TestFixture.TARGET_WORD_WITH_ID));

        var result = queryService.getTargetWord(TestFixture.TARGETWORD_ID);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldThrowExceptionIfTargetwordNotExists() {
        var queryService = new QueryService(targetWordRepository, autocompleteRepository);
        Mockito.when(targetWordRepository.findById(TestFixture.TARGETWORD_ID)).thenReturn(Optional.ofNullable(TestFixture.TARGET_WORD_WITH_ID));

        Throwable thrown = catchThrowable(() -> {
            queryService.getTargetWord("WRONG_ID");
        });

        assertThat(thrown).isInstanceOf(TargetWordNotFoundException.class);
    }
}