package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Connection;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Node;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Source;
import com.google.common.collect.ImmutableList;
import com.google.common.graph.NetworkBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SourceTest {

    public static final String SOURCE_ID_1 = "Source-1";
    public static final String SOURCE_NAME = "Source-name";
    public static final Node NODE_1 = Node.of("NODE_1", 1, "TEST_NODE_1", "NOUN", 0.4f, 100,
            0.3f);
    public static final Node NODE_2 = Node.of("NODE_1", 1, "TEST_NODE_1", "NOUN", 0.4f, 100,
            0.4f);

    @Test
    public void shouldInstantiateSource() {
        var source = Source.of(SOURCE_ID_1, SOURCE_NAME, ImmutableList.of());

        assertThat(source.getId()).isEqualTo(SOURCE_ID_1);
        assertThat(source.getName()).isEqualTo(SOURCE_NAME);
        assertThat(source.getNetworks()).isEmpty();
    }

    @Test
    public void shouldInstantiateSourceWithNetwork() {
        var node1 = NODE_1;
        var node2 = NODE_2;
        var network = NetworkBuilder.undirected()
                .allowsParallelEdges(true)
                .<Node, Connection>immutable()
                .addEdge(node1, node2, Connection.of("EDGE_1", 0.3f))
                .build();
        var egoNetwork = EgoNetwork.of("NETWORK_1", "NETWORK_TEST_1", 2020, "CORPUS_1", "SOURCE_1", 200, 0.5f, 0.4f, network);

        var source = Source.of(SOURCE_ID_1, SOURCE_NAME, ImmutableList.of(egoNetwork));

        assertThat(source.getId()).isEqualTo(SOURCE_ID_1);
        assertThat(source.getName()).isEqualTo(SOURCE_NAME);
        assertThat(source.getNetworks()).contains(egoNetwork);
    }
}