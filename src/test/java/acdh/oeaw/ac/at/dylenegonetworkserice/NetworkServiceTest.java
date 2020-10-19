package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import java.io.IOException;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.EGO_NETWORK_ID;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class NetworkServiceTest {

    @Mock
    EgoNetworkRepository egoNetworkRepository;

    @Test
    public void shouldGetNetworkById() throws IOException {
        var networkService = new NetworkService(egoNetworkRepository);
        var network = EgoNetwork.of("1", 2020, "AMC", "DERSTANDARD", 100, 0.5f, 0.56f,
                ImmutableList.of(), ImmutableList.of());
        Mockito.when(egoNetworkRepository.findById("1")).thenReturn(java.util.Optional.of(network));
        //networkService.putEgoNetwork(NETWORK);

        var result = networkService.getNetworkById(EGO_NETWORK_ID);

        assertThat(result).isNotNull();
    }
}
