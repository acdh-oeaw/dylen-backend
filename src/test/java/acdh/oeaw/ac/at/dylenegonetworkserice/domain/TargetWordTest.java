package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import java.io.IOException;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TargetWordTest {

    @Test
    void shouldInstantiateTargetWord() {
        var targetWord = TargetWord.of(NODE_TEXT_1, NODE_POS,
                CORPUS_NAME, SOURCE_NAME, ImmutableList.of(TestFixture.NETWORK));

        assertThat(targetWord.getText()).isEqualTo(NODE_TEXT_1);
        assertThat(targetWord.getPos()).isEqualTo(NODE_POS);
        assertThat(targetWord.getCorpus()).isEqualTo(CORPUS_NAME);
        assertThat(targetWord.getSource()).isEqualTo(SOURCE_NAME);
        assertThat(targetWord.getNetworks().isEmpty()).isFalse();
    }

    @Test
    void shouldInstantiateTargetWordWithId() {
        var targetWord = TargetWord.of(TARGETWORD_ID, NODE_TEXT_1, NODE_POS,
                CORPUS_NAME, SOURCE_NAME, ImmutableList.of(TestFixture.NETWORK));

        assertThat(targetWord.getId()).isEqualTo(TARGETWORD_ID);
        assertThat(targetWord.getText()).isEqualTo(NODE_TEXT_1);
        assertThat(targetWord.getPos()).isEqualTo(NODE_POS);
        assertThat(targetWord.getCorpus()).isEqualTo(CORPUS_NAME);
        assertThat(targetWord.getSource()).isEqualTo(SOURCE_NAME);
        assertThat(targetWord.getNetworks().isEmpty()).isFalse();
    }

    @Test
    void fromJson() throws IOException {
        var mapper = new ObjectMapper();

        var targetWord = mapper.readValue(ResourceUtils.getFile("classpath:AMC/APA_Balkanroute-n.json"),
                TargetWord.class);

        Assertions.assertThat(targetWord).isNotNull();
    }
}