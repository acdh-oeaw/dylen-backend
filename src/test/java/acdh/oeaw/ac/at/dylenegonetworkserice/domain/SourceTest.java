package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.google.common.collect.ImmutableList;
import com.google.common.graph.ImmutableNetwork;
import com.google.common.graph.NetworkBuilder;
import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SourceTest {


    @Test
    public void shouldInstantiateSource() {
        var source = Source.of(SOURCE_ID_1, SOURCE_NAME, ImmutableList.of());

        assertThat(source.getId()).isEqualTo(SOURCE_ID_1);
        assertThat(source.getName()).isEqualTo(SOURCE_NAME);
        assertThat(source.getNetworks()).isEmpty();
    }

    @Test
    public void shouldInstantiateSourceWithNetwork() {
        var network = NETWORK;
        var egoNetwork = EgoNetwork.of(EGO_NETWORK_ID, EGO_NETWORK_NAME, EGO_NETWORK_YEAR, "CORPUS_1", "SOURCE_1", 200, 0.5f, 0.4f, network);

        var source = Source.of(SOURCE_ID_1, SOURCE_NAME, ImmutableList.of(egoNetwork));

        assertThat(source.getId()).isEqualTo(SOURCE_ID_1);
        assertThat(source.getName()).isEqualTo(SOURCE_NAME);
        assertThat(source.getNetworks()).contains(egoNetwork);
    }
}