package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.AppConfig;
import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.NetworkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}