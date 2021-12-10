package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TimeSeries;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TimeSeriesMetric;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.*;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class TestFixture {
    public static final String CORPUS_ID = "TEST_ID";
    public static final String CORPUS_NAME = "TEST_NAME";
    public static final String NODE_ID_1 = "NODE_1";
    public static final int CLUTER_ID_1 = 1;
    public static final String NODE_TEXT_1 = "TEST_NODE_1";
    public static final String NODE_POS = "NOUN";
    public static final float SIMILARITY = 0.4f;
    public static final int ABSOLUTE_FREQUENCY = 10;
    public static double NORMALISED_FREQUENCY = 0.002;
    public static final GeneralTargetWord GENERAL_TARGET_WORD = GeneralTargetWord.of("party", "FPOe",
            GeneralNetwork.of("2006", null, null, null));
    public static final GeneralTargetWordSpeaker GENERAL_TARGET_WORD_SPEAKER = GeneralTargetWordSpeaker.of("speaker", "00041", "Aumayr Anna Elisabeth siehe Achatz Anna Elisabeth",
            ImmutableList.of(GeneralNetwork.of("1996", null, null, null),
                    GeneralNetwork.of("1997", null, null, null),
                    GeneralNetwork.of("1998", null, null, null)));
    public static final NodeMetric NODE_METRICS = NodeMetric.of(0.21, 0.51, 0.001, 0.077, 0.012, 0.043, 0.444, 0.435);
    public static final Node NODE_1 = Node.of(NODE_ID_1, CLUTER_ID_1, NODE_TEXT_1, NODE_POS, SIMILARITY, NODE_METRICS, ABSOLUTE_FREQUENCY, NORMALISED_FREQUENCY);
    public static final String NODE_ID_2 = "NODE_2";
    public static final int CLUTER_ID_2 = 2;
    public static final String NODE_TEXT_2 = "TEST_NODE_2";
    public static final float SIMILARITY_2 = 0.4f;
    public static final Node NODE_2 = Node.of(NODE_ID_2, CLUTER_ID_2, NODE_TEXT_2, NODE_POS, SIMILARITY_2, NODE_METRICS, ABSOLUTE_FREQUENCY, NORMALISED_FREQUENCY);
    public static final String CONNECTION_ID = "EDGE_1";
    public static final float CONNECTION_SIMILARITY = 0.3f;
    public static final Edge EDGE = Edge.of(CONNECTION_ID, NODE_ID_1, NODE_ID_2,
            CONNECTION_SIMILARITY);
    public static final String EGO_NETWORK_ID = "NETWORK_1";
    public static final String EGO_NETWORK_ID_2 = "NETWORK_2";

    public static final String EGO_NETWORK_NAME = "NETWORK_TEST_1";
    public static final int EGO_NETWORK_YEAR = 2020;
    public static final int EGO_NETWORK_YEAR_2 = 2021;

    public static final ImmutableList<Node> NODES = ImmutableList.of(NODE_1, NODE_2);
    public static final ImmutableList<Edge> EDGES = ImmutableList.of(EDGE);
    public static final NetworkMetric NETWORK_METRIC= NetworkMetric.of(0.123, 0.435);

    public static final String AMC_CORPUS = "AMC";
    public static final String SOURCE_NAME = "Falter";
    public static final String TARGETWORD_TEXT = "ASYL";
    public static final String TARGETWORD_ID = "TARGET_ID";

    public static final TimeSeriesMetric freqDiffNorm = TimeSeriesMetric.of(ImmutableList.of(0.1,0.2), ImmutableList.of(0.3,0.4), ImmutableList.of(0.5,0.6));
    public static final TimeSeriesMetric jaccardSimilarity = TimeSeriesMetric.of(ImmutableList.of(0.1,0.2), ImmutableList.of(0.3,0.4), ImmutableList.of(0.5,0.6));
    public static final TimeSeriesMetric frobeniusSimilarity = TimeSeriesMetric.of(ImmutableList.of(0.1,0.2), ImmutableList.of(0.3,0.4), ImmutableList.of(0.5,0.6));
    public static final TimeSeriesMetric rankdcgSimilarity = TimeSeriesMetric.of(ImmutableList.of(0.1,0.2), ImmutableList.of(0.3,0.4), ImmutableList.of(0.5,0.6));
    public static final TimeSeriesMetric localNeighbourhoodSimilarity = TimeSeriesMetric.of(ImmutableList.of(0.1,0.2), ImmutableList.of(0.3,0.4), ImmutableList.of(0.5,0.6));

    public static final TimeSeries TIME_SERIES = TimeSeries.of(freqDiffNorm, jaccardSimilarity, frobeniusSimilarity, rankdcgSimilarity, localNeighbourhoodSimilarity);
    public static final EgoNetwork NETWORK = EgoNetwork.of(EGO_NETWORK_ID,
            EGO_NETWORK_YEAR, NODES, EDGES, NETWORK_METRIC, ABSOLUTE_FREQUENCY, NORMALISED_FREQUENCY);
    public static final EgoNetwork NETWORK_2 = EgoNetwork.of(EGO_NETWORK_ID_2,
            EGO_NETWORK_YEAR_2, NODES, EDGES, NETWORK_METRIC, ABSOLUTE_FREQUENCY, NORMALISED_FREQUENCY);
    public static final TargetWord TARGET_WORD_WITH_ID = TargetWord.of(TARGETWORD_ID, TARGETWORD_TEXT, "NOUN", CORPUS_NAME, SOURCE_NAME, ImmutableList.of(NETWORK), TIME_SERIES);
    public static final TargetWord TARGET_WORD = TargetWord.of(TARGETWORD_TEXT, "NOUN", AMC_CORPUS, SOURCE_NAME, ImmutableList.of(NETWORK), TIME_SERIES);
    public static final Corpus CORPUS_1 = Corpus.of("1", AMC_CORPUS, ImmutableList.of(Source.of(TestFixture.SOURCE_NAME,
            ImmutableList.of(TestFixture.TARGET_WORD))));
}
