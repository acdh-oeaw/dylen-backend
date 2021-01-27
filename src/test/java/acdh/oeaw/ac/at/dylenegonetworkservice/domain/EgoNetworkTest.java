package acdh.oeaw.ac.at.dylenegonetworkservice.domain;

import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkservice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EgoNetworkTest {

    @Test
    public void shouldInstantiateEgoNetwork() {
        var egoNetwork = EgoNetwork.of(EGO_NETWORK_YEAR, NODES, CONNECTIONS);

        assertThat(egoNetwork.getYear()).isEqualTo(EGO_NETWORK_YEAR);
        assertThat(egoNetwork.getNodes()).containsExactly(NODE_1, NODE_2);
        assertThat(egoNetwork.getConnections()).containsExactly(CONNECTION);
    }
}