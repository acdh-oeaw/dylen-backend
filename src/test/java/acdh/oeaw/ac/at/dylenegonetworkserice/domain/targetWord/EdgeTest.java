package acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord;

import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EdgeTest {
    @Test
    public void shouldInstantiateConnection() {
        var connection = Edge.of(CONNECTION_ID, NODE_ID_1, NODE_ID_2, CONNECTION_SIMILARITY);

        assertThat(connection.getId()).isEqualTo(CONNECTION_ID);
        assertThat(connection.getSimilarity()).isEqualTo(CONNECTION_SIMILARITY);
    }
}