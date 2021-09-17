package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.TargetWordNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        var targetWords = new SliceImpl<TargetWord>(ImmutableList.of(TARGET_WORD_WITH_ID));
        var pageRequest = PageRequest.of(0,5);
        Mockito.when(targetWordRepository.findByCorpusAndSource(CORPUS_NAME, SOURCE_NAME, pageRequest)).thenReturn(targetWords);
        var networkService = new NetworkService(targetWordRepository);

        var result = networkService.getTargetWordsOfCorpusAndSource(CORPUS_NAME, SOURCE_NAME, pageRequest);

        assertThat(result.getTargetWords()).contains(TARGET_WORD_WITH_ID);
        assertThat(result.getTargetWords().get(0).getNetworks().size()).isEqualTo(2);
        assertThat(result.getTargetWords().get(0).getNetworks().get(0).getYear()).isEqualTo(EGO_NETWORK_YEAR);
    }
}
