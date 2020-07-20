package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EgoNetworkTest {



    @Test
    public void shouldInstantiateEgoNetwork() {
        var egoNetwork = EgoNetwork.of(EGO_NETWORK_ID, EGO_NETWORK_NAME, EGO_NETWORK_YEAR, CORPUS_ID, SOURCE_ID_1, 20,
                0.3f, 0.3f, NODES, CONNECTIONS);

        assertThat(egoNetwork.getId()).isEqualTo(EGO_NETWORK_ID);
        assertThat(egoNetwork.getText()).isEqualTo(EGO_NETWORK_NAME);
        assertThat(egoNetwork.getYear()).isEqualTo(EGO_NETWORK_YEAR);
        assertThat(egoNetwork.getCorpusId()).isEqualTo(CORPUS_ID);
        assertThat(egoNetwork.getSourceId()).isEqualTo(SOURCE_ID_1);
        assertThat(egoNetwork.getAbsFreq()).isEqualTo(20);
        assertThat(egoNetwork.getRelFreq()).isEqualTo(0.3f);
        assertThat(egoNetwork.getThreshold()).isEqualTo(0.3f);
        assertThat(egoNetwork.getNodes()).containsExactly(NODE_1, NODE_2);
        assertThat(egoNetwork.getConnections()).containsExactly(CONNECTION);
    }
}