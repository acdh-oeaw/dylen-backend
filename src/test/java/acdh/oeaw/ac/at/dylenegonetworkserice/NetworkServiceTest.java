package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.EGO_NETWORK_ID;
import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.NETWORK;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetworkServiceTest {

    @Autowired
    EgoNetworkRepository egoNetworkRepository;
    @Test
    public void shouldGetNetworkById() throws IOException {

        var networkService = new NetworkService(egoNetworkRepository);
        networkService.putEgoNetwork(NETWORK);
        var network = networkService.getNetworkById(EGO_NETWORK_ID);

        assertThat(network).isNotNull();
    }
}
