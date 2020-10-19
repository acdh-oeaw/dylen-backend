package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.EgoNetworkNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import java.io.IOException;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.EGO_NETWORK_ID;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class NetworkServiceTest {

    @Mock
    EgoNetworkRepository egoNetworkRepository;

    @Test
    public void shouldGetNetworkById() throws IOException {
        var networkService = new NetworkService(egoNetworkRepository);
        var network = EgoNetwork.of("1", 2020, "AMC", "DERSTANDARD", 100, 0.5f, 0.56f,
                ImmutableList.of(), ImmutableList.of());
        Mockito.when(egoNetworkRepository.findById(EGO_NETWORK_ID)).thenReturn(java.util.Optional.of(network));

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
}
