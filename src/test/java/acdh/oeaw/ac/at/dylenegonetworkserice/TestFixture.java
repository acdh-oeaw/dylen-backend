package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Connection;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Node;
import com.google.common.collect.ImmutableList;
import com.google.common.graph.ImmutableNetwork;
import com.google.common.graph.NetworkBuilder;

public class TestFixture {
    public static final String SOURCE_ID_1 = "Source-1";
    public static final String SOURCE_NAME = "Source-name";


    public static final String CORPUS_ID = "TEST_ID";
    public static final String CORPUS_NAME = "TEST_NAME";

    public static final String NODE_ID_1 = "NODE_1";
    public static final int CLUTER_ID_1 = 1;
    public static final String NODE_TEXT_1 = "TEST_NODE_1";
    public static final String NODE_POS = "NOUN";
    public static final float SIMILARITY = 0.4f;
    public static final int ABS_FREQ = 100;
    public static final float REL_FREQ = 0.3f;
    public static final Node NODE_1 = Node.of(NODE_ID_1, CLUTER_ID_1, NODE_TEXT_1, NODE_POS, SIMILARITY, ABS_FREQ,
            REL_FREQ);
    public static final String NODE_ID_2 = "NODE_2";
    public static final int CLUTER_ID_2 = 2;
    public static final String NODE_TEXT_2 = "TEST_NODE_2";
    public static final float SIMILARITY_2 = 0.4f;
    public static final int ABS_FREQ_2 = 102;
    public static final float REL_FREQ_2 = 0.43f;
    public static final Node NODE_2 = Node.of(NODE_ID_2, CLUTER_ID_2, NODE_TEXT_2, NODE_POS, SIMILARITY_2, ABS_FREQ_2,
            REL_FREQ_2);

    public static final String CONNECTION_ID = "EDGE_1";
    public static final float CONNECTION_SIMILARITY = 0.3f;
    public static final Connection CONNECTION = Connection.of(CONNECTION_ID, NODE_ID_1, NODE_ID_2, CONNECTION_SIMILARITY);

    public static final String EGO_NETWORK_ID = "NETWORK_1";
    public static final String EGO_NETWORK_NAME = "NETWORK_TEST_1";
    public static final int EGO_NETWORK_YEAR = 2020;

    public static final ImmutableList<Node> NODES = ImmutableList.of(NODE_1, NODE_2);
    public static final ImmutableList<Connection> CONNECTIONS = ImmutableList.of(CONNECTION);

    public static final EgoNetwork NETWORK = EgoNetwork.of(EGO_NETWORK_ID,
            EGO_NETWORK_NAME, EGO_NETWORK_YEAR, CORPUS_ID,
            SourceService.SourceEnum.STANDARD.getName(), 201, 0.2f, 0.5f, NODES, CONNECTIONS);

}
