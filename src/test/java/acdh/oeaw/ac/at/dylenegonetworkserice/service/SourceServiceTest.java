package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SourceServiceTest {

    @Test
    public void shouldReturnIdByName() {
        var sourceName = "Standard";
        var sourceService = new SourceService();

        var sourceId = sourceService.getIdByName(sourceName);

        assertThat(sourceId).isNotNull();
    }
}
