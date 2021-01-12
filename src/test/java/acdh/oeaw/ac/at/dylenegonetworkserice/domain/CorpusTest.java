package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.CORPUS_ID;
import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.CORPUS_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class CorpusTest {

    @Test
    public void shouldInstantiateCorpus() {

        var corpus = Corpus.of(CORPUS_ID, CORPUS_NAME, ImmutableList.of());

        assertThat(corpus.getId()).isEqualTo(CORPUS_ID);
        assertThat(corpus.getName()).isEqualTo(CORPUS_NAME);
        assertThat(corpus.getSources()).isEmpty();
    }
}
