package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.CONNECTION_ID;
import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.CONNECTION_SIMILARITY;
import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionTest {
    @Test
    public void shouldInstantiateConnection() {
        var connection = Connection.of(CONNECTION_ID, CONNECTION_SIMILARITY);

        assertThat(connection.getId()).isEqualTo(CONNECTION_ID);
        assertThat(connection.getSimilarity()).isEqualTo(CONNECTION_SIMILARITY);
    }
}