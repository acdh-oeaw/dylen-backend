package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CorpusServiceTest {

    @Mock
    EgoNetworkRepository egoNetworkRepository;

    @Test
    public void shouldRetrieveAllCorpora() {
        var corpusService = new CorpusService(egoNetworkRepository);
        Mockito.when(egoNetworkRepository.findEgoNetworksByCorpus(AMC_CORPUS)).thenReturn(ImmutableList.of(NETWORK));
        var corpora = corpusService.getAllCorpora();

        assertThat(corpora).isNotEmpty();
    }
}