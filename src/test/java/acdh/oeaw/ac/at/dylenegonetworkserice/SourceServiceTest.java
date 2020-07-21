package acdh.oeaw.ac.at.dylenegonetworkserice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

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
