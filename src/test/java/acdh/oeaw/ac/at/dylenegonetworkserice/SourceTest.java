package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Source;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SourceTest {

    @Test
    public void shouldInstantiateSource() {

        String id = "Source-1";
        String name = "Source-name";
        var source = Source.of(id, name, ImmutableList.of());

        assertThat(source.getId()).isEqualTo(id);
        assertThat(source.getName()).isEqualTo(name);
        assertThat(source.getNetworks()).isEmpty();
    }
}