package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.*;
import com.google.common.collect.ImmutableList;

public class TestFixture {
    public static final String CORPUS_ID = "TEST_ID";
    public static final String CORPUS_NAME = "TEST_NAME";
    public static final String NODE_ID_1 = "NODE_1";
    public static final int CLUTER_ID_1 = 1;
    public static final String NODE_TEXT_1 = "TEST_NODE_1";
    public static final String NODE_POS = "NOUN";
    public static final float SIMILARITY = 0.4f;
    public static final NodeMetric NODE_METRICS = NodeMetric.of(0.21, 0.51, 0.001, 0.077, 0.012, 0.043, 0.444, 0.435);
    public static final Node NODE_1 = Node.of(NODE_ID_1, CLUTER_ID_1, NODE_TEXT_1, NODE_POS, SIMILARITY, NODE_METRICS);
    public static final String NODE_ID_2 = "NODE_2";
    public static final int CLUTER_ID_2 = 2;
    public static final String NODE_TEXT_2 = "TEST_NODE_2";
    public static final float SIMILARITY_2 = 0.4f;
    public static final Node NODE_2 = Node.of(NODE_ID_2, CLUTER_ID_2, NODE_TEXT_2, NODE_POS, SIMILARITY_2, NODE_METRICS);
    public static final String CONNECTION_ID = "EDGE_1";
    public static final float CONNECTION_SIMILARITY = 0.3f;
    public static final Edge EDGE = Edge.of(CONNECTION_ID, NODE_ID_1, NODE_ID_2,
            CONNECTION_SIMILARITY);
    public static final String EGO_NETWORK_ID = "NETWORK_1";
    public static final String EGO_NETWORK_NAME = "NETWORK_TEST_1";
    public static final int EGO_NETWORK_YEAR = 2020;
    public static final ImmutableList<Node> NODES = ImmutableList.of(NODE_1, NODE_2);
    public static final ImmutableList<Edge> EDGES = ImmutableList.of(EDGE);
    public static final NetworkMetric NETWORK_METRIC= NetworkMetric.of(0.123, 0.435);
    public static final EgoNetwork NETWORK = EgoNetwork.of(EGO_NETWORK_ID,
             EGO_NETWORK_YEAR, NODES, EDGES, NETWORK_METRIC);
    public static final String AMC_CORPUS = "AMC";
    public static final String SOURCE_NAME = "Falter";
    public static final String TARGETWORD_TEXT = "ASYL";
    public static final String TARGETWORD_ID = "TARGET_ID";
    public static final TargetWord TARGET_WORD_WITH_ID = TargetWord.of(TARGETWORD_ID, TARGETWORD_TEXT, "NOUN", CORPUS_NAME, SOURCE_NAME, ImmutableList.of(NETWORK));
    public static final TargetWord TARGET_WORD = TargetWord.of(TARGETWORD_TEXT, "NOUN", AMC_CORPUS, SOURCE_NAME, ImmutableList.of(NETWORK));
    public static final Corpus CORPUS_1 = Corpus.of("1", AMC_CORPUS, ImmutableList.of(Source.of(TestFixture.SOURCE_NAME,
            ImmutableList.of(TestFixture.TARGET_WORD))));


}
