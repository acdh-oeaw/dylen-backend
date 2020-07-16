package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EgoNetworkTest {
    @Test
    public void shouldInstantiateEgoNetwork() {
        var egoNetwork = EgoNetwork.of(EGO_NETWORK_ID, EGO_NETWORK_NAME, EGO_NETWORK_YEAR, CORPUS_ID, SOURCE_ID_1, 20,
                0.3f, 0.3f, NETWORK);

        assertThat(egoNetwork.getId()).isEqualTo(EGO_NETWORK_ID);
        assertThat(egoNetwork.getText()).isEqualTo(EGO_NETWORK_NAME);
        assertThat(egoNetwork.getYear()).isEqualTo(EGO_NETWORK_YEAR);
        assertThat(egoNetwork.getCorpusId()).isEqualTo(CORPUS_ID);
        assertThat(egoNetwork.getSourceId()).isEqualTo(SOURCE_ID_1);
        assertThat(egoNetwork.getAbsFreq()).isEqualTo(20);
        assertThat(egoNetwork.getRelFreq()).isEqualTo(0.3f);
        assertThat(egoNetwork.getThreshold()).isEqualTo(0.3f);
        assertThat(egoNetwork.getNetwork()).isEqualTo(NETWORK);
    }
}