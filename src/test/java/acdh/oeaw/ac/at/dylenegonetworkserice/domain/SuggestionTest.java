package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.SOURCE_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SuggestionTest {
    @Test
    public void shouldInstantiateSource() {
        var suggestion = Suggestion.of(TestFixture.TARGETWORD_ID, TestFixture.TARGETWORD_TEXT);

        assertThat(suggestion.getId()).isEqualTo(TestFixture.TARGETWORD_ID);
        assertThat(suggestion.getText()).isEqualTo(TestFixture.TARGETWORD_TEXT);
    }

}