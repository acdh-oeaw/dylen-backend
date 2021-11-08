package acdh.oeaw.ac.at.dylenegonetworkserice.service.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.generalNetworks.GeneralNetworkRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord.TargetWordRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord.CorpusService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.AMC_CORPUS;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class GeneralNetworkTest {

    @Mock
    GeneralNetworkRepository generalNetworkRepository;

    @Test
    public void repositoryIsMocked() {
        assertThat(generalNetworkRepository).isNotNull();
    }

}