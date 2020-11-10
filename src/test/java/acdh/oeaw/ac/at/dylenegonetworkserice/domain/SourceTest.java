package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SourceTest {

    @Test
    public void shouldInstantiateSource() {
        var source = Source.of(SOURCE_NAME, ImmutableList.of());

        assertThat(source.getName()).isEqualTo(SOURCE_NAME);
        assertThat(source.getNetworks()).isEmpty();
    }

    @Test
    public void shouldInstantiateSourceWithList() {
        var network = NETWORK;
        var egoNetwork = EgoNetwork.of(EGO_NETWORK_NAME, EGO_NETWORK_YEAR, CORPUS_NAME,
                SOURCE_NAME, 200, 0.5f, 0.4f, NODES, CONNECTIONS);
        var targetWord = TargetWord.of(EGO_NETWORK_NAME, null, ImmutableList.of(egoNetwork));

        var source = Source.of(SOURCE_NAME, ImmutableList.of(egoNetwork));

        assertThat(source.getName()).isEqualTo(SOURCE_NAME);
        assertThat(source.getTargetWords()).contains(targetWord);
        assertThat(source.getNetworks()).contains(egoNetwork);
    }
}