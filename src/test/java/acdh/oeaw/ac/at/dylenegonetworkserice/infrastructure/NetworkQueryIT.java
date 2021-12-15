package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.NetworkQuery;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.NetworkService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NetworkQueryIT {

    @Mock
    private TargetWordRepository targetWordRepository;

    @InjectMocks
    private NetworkService networkService;

    @Test
    void shouldQueryNetworkByTargetword() throws IOException {
        var query = new NetworkQuery(networkService);
        when(targetWordRepository.findById(TestFixture.TARGETWORD_ID)).thenReturn(Optional.of(TestFixture.TARGET_WORD_WITH_ID));

        var network = query.getNetwork(TestFixture.TARGETWORD_ID, TestFixture.EGO_NETWORK_YEAR);

        verify(targetWordRepository, times(1)).findById(TestFixture.TARGETWORD_ID);
        assertThat(network).isEqualTo(TestFixture.TARGET_WORD_WITH_ID.getNetworks().get(0));
    }

    @Test
    void shouldRetrieveNetworkByCorpusAndSource() {
        var query = new NetworkQuery(networkService);
        var pageRequest = PageRequest.of(0, 5);
        when(targetWordRepository.findByCorpusAndSource(TestFixture.AMC_CORPUS, "KLEINE", pageRequest))
                .thenReturn(new SliceImpl<TargetWord>(ImmutableList.of(TestFixture.TARGET_WORD_WITH_ID)));

        var networks = query.getNetworksByCorpusAndSource(TestFixture.AMC_CORPUS, "KLEINE", 0, 5);

        verify(targetWordRepository, times(1)).findByCorpusAndSource(TestFixture.AMC_CORPUS, "KLEINE", pageRequest);
        assertThat(networks).isNotNull();
        assertThat(networks.getTargetWords()).isEqualTo(ImmutableList.of(TestFixture.TARGET_WORD_WITH_ID));
        assertThat(networks.getTargetWords().get(0).getTimeSeries().getFreqDiffNorm().getFirstYear().get(0)).isEqualTo(TestFixture.freqDiffNorm.getFirstYear().get(0));
    }
}