package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.EgoNetworkNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class NetworkServiceTest {

    @Mock
    EgoNetworkRepository egoNetworkRepository;

    @Test
    public void shouldGetNetworkById() throws IOException {
        var networkService = new NetworkService(egoNetworkRepository);
        Mockito.when(egoNetworkRepository.findById(EGO_NETWORK_ID)).thenReturn(java.util.Optional.of(NETWORK));

        var result = networkService.getNetworkById(EGO_NETWORK_ID);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldThrowEgoNetWorkNotFoundException() throws IOException {
        var networkService = new NetworkService(egoNetworkRepository);
        Mockito.when(egoNetworkRepository.findById("1")).thenReturn(java.util.Optional.empty());

        Throwable thrown = catchThrowable(() -> { networkService.getNetworkById(EGO_NETWORK_ID); });

        assertThat(thrown).isInstanceOf(EgoNetworkNotFoundException.class);
    }

    @Test
    public void shouldReturnNetworkByTargetWord() throws IOException {
        var networkService = new NetworkService(egoNetworkRepository);
        Mockito.when(egoNetworkRepository.findByTargetWord(EGO_NETWORK_NAME)).thenReturn(ImmutableList.of(NETWORK));

        var result = networkService.getNetworkByTargetWord(EGO_NETWORK_NAME);
    }
}
