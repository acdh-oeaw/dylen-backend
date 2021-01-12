package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.TargetWordNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class NetworkServiceTest {

    @Mock
    TargetWordRepository targetWordRepository;


    @Test
    public void shouldThrowEgoNetWorkNotFoundException() throws IOException {
        var networkService = new NetworkService(targetWordRepository);
        Mockito.when(targetWordRepository.findById("1")).thenReturn(java.util.Optional.empty());

        Throwable thrown = catchThrowable(() -> { networkService.getTargetWordById(EGO_NETWORK_ID); });

        assertThat(thrown).isInstanceOf(TargetWordNotFoundException.class);
    }

    @Test
    public void shouldReturnNetworkByTargetWord() throws IOException {
        var networkService = new NetworkService(targetWordRepository);
        //Mockito.when(targetWordRepository.findByText(EGO_NETWORK_NAME)).thenReturn(ImmutableList.of(NETWORK));

        var result = networkService.getNetworkByTargetWord(EGO_NETWORK_NAME);
    }

    @Test
    public void shouldRetrieveTargetWordsOfCorpusAndSource() throws IOException {
        var targetWords = ImmutableList.of(TARGET_WORD_WITH_ID);
        Mockito.when(targetWordRepository.findByCorpusAndSource(CORPUS_NAME, SOURCE_NAME)).thenReturn(targetWords);
        var networkService = new NetworkService(targetWordRepository);

        var result = networkService.getTargetWordsOfCorpusAndSource(CORPUS_NAME, SOURCE_NAME);

        assertThat(result).contains(TARGET_WORD_WITH_ID);
    }
}
