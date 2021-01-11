package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SourceTest {

    @Test
    public void shouldInstantiateSource() {
        var source = Source.of(SOURCE_NAME, ImmutableList.of());

        assertThat(source.getName()).isEqualTo(SOURCE_NAME);
        assertThat(source.getTargetWords()).isEmpty();
    }

    @Test
    public void shouldInstantiateSourceWithList() {

        var source = Source.of(SOURCE_NAME, ImmutableList.of(TARGET_WORD));

        assertThat(source.getName()).isEqualTo(SOURCE_NAME);
        assertThat(source.getTargetWords()).contains(TARGET_WORD);
        assertThat(source.getTargetWords()).allMatch(targetWord -> targetWord.getSource().equals(SOURCE_NAME));
    }
}