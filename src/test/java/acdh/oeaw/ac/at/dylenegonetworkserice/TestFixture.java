package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Connection;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Node;
import com.google.common.graph.ImmutableNetwork;
import com.google.common.graph.NetworkBuilder;

public class TestFixture {
    public static final String SOURCE_ID_1 = "Source-1";
    public static final String SOURCE_NAME = "Source-name";


    public static final String CORPUS_ID = "TEST_ID";
    public static final String CORPUS_NAME = "TEST_NAME";

    public static final Node NODE_1 = Node.of("NODE_1", 1, "TEST_NODE_1", "NOUN", 0.4f, 100,
            0.3f);
    public static final Node NODE_2 = Node.of("NODE_1", 1, "TEST_NODE_1", "NOUN", 0.4f, 100,
            0.4f);

    public static final String CONNECTION_ID = "EDGE_1";
    public static final float CONNECTION_SIMILARITY = 0.3f;
    public static final Connection CONNECTION = Connection.of(CONNECTION_ID, CONNECTION_SIMILARITY);

    public static final String EGO_NETWORK_ID = "NETWORK_1";
    public static final String EGO_NETWORK_NAME = "NETWORK_TEST_1";
    public static final int EGO_NETWORK_YEAR = 2020;


    public static final ImmutableNetwork<Node, Connection> NETWORK = NetworkBuilder.undirected()
            .allowsParallelEdges(true)
            .<Node, Connection>immutable()
            .addEdge(NODE_1, NODE_2, CONNECTION)
            .build();

}
