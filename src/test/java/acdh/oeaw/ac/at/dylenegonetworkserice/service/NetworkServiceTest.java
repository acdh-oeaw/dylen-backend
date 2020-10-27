package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.EgoNetworkNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Disabled;
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
    EgoNetworkRepository egoNetworkRepository;

    @Disabled
    @Test
    public void shouldGetNetworkById() throws IOException {
        var networkService = new NetworkServiceDummy(egoNetworkRepository);
        Mockito.when(egoNetworkRepository.findById(EGO_NETWORK_ID)).thenReturn(java.util.Optional.of(NETWORK));

        var result = networkService.getNetworkById(EGO_NETWORK_ID);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldThrowEgoNetWorkNotFoundException() throws IOException {
        var networkService = new NetworkServiceDummy(egoNetworkRepository);
        Mockito.when(egoNetworkRepository.findById("1")).thenReturn(java.util.Optional.empty());

        Throwable thrown = catchThrowable(() -> { networkService.getNetworkById(EGO_NETWORK_ID); });

        assertThat(thrown).isInstanceOf(EgoNetworkNotFoundException.class);
    }

    @Test
    public void shouldReturnNetworkByTargetWord() throws IOException {
        var networkService = new NetworkServiceDummy(egoNetworkRepository);
        Mockito.when(egoNetworkRepository.findByText(EGO_NETWORK_NAME)).thenReturn(ImmutableList.of(NETWORK));

        var result = networkService.getNetworkByTargetWord(EGO_NETWORK_NAME);
    }
}
