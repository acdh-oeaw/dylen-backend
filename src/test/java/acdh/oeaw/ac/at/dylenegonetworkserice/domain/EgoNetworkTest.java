package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EgoNetworkTest {

    @Test
    public void shouldInstantiateEgoNetwork() {
        var egoNetwork = EgoNetwork.of(EGO_NETWORK_YEAR, NODES, EDGES, NETWORK_METRIC, TIME_SERIES);

        assertThat(egoNetwork.getYear()).isEqualTo(EGO_NETWORK_YEAR);
        assertThat(egoNetwork.getNodes()).containsExactly(NODE_1, NODE_2);
        assertThat(egoNetwork.getEdges()).containsExactly(EDGE);
        assertThat(egoNetwork.getMetrics().getDensity()).isEqualTo(NETWORK_METRIC.getDensity());
        assertThat(egoNetwork.getMetrics().getClusteringCoefficient()).isEqualTo(NETWORK_METRIC.getClusteringCoefficient());
        assertThat(egoNetwork.getTimeSeries().getFreqDiffNorm().getFirstYear().size()).isEqualTo(2);
    }
}