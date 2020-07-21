package acdh.oeaw.ac.at.dylenegonetworkserice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.EGO_NETWORK_ID;
import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.NETWORK;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class NetworkServiceTest {


    @Test
    public void shouldGetNetworkById() throws IOException {
        var networkService = new NetworkService();
        networkService.putEgoNetwork(NETWORK);
        var network = networkService.getNetworkById(EGO_NETWORK_ID);

        assertThat(network).isNotNull();
    }
}
