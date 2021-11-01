package acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord;


import org.junit.Test;
import org.springframework.data.geo.Metric;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    @Test
    public void shouldInstantiateNode() {
        var node = Node.of(NODE_ID_1, CLUTER_ID_1, NODE_TEXT_1, NODE_POS, SIMILARITY, NODE_METRICS);

        assertThat(node.getId()).isEqualTo(NODE_ID_1);
        assertThat(node.getClusterId()).isEqualTo(CLUTER_ID_1);
        assertThat(node.getText()).isEqualTo(NODE_TEXT_1);
        assertThat(node.getPos()).isEqualTo(NODE_POS);
        assertThat(node.getSimilarity()).isEqualTo(SIMILARITY);
        assertThat(node.getMetrics()).isEqualTo(NODE_METRICS);
    }
}