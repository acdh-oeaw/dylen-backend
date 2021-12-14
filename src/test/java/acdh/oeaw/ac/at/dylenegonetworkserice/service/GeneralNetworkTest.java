package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.GeneralNetworkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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