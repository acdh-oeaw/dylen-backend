package acdh.oeaw.ac.at.dylenegonetworkserice;

import org.junit.Test;

import java.io.IOException;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.EGO_NETWORK_ID;
import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.NETWORK;
import static org.assertj.core.api.Assertions.assertThat;

public class NetworkServiceTest {


    @Test
    public void shouldGetNetworkById() throws IOException {
        var networkService = new NetworkService();
        networkService.putEgoNetwork(NETWORK);
        var network = networkService.getNetworkById(EGO_NETWORK_ID);

        assertThat(network).isNotNull();
    }
}
