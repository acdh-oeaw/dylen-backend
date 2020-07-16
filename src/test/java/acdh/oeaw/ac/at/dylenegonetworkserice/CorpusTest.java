package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CorpusTest {

    @Test
    public void shouldInstantiateCorpus() {

        String id = "TEST_ID";
        String name = "TEST_NAME";
        var corpus = Corpus.of(id, name, ImmutableList.of());

        assertThat(corpus.getId()).isEqualTo(id);
        assertThat(corpus.getName()).isEqualTo(name);
        assertThat(corpus.getSources()).isEmpty();
    }
}
