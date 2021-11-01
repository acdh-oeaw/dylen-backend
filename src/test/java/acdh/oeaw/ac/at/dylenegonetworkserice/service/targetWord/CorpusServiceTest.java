package acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord.TargetWordRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CorpusServiceTest {

    @Mock
    TargetWordRepository targetWordRepository;

    @Test
    public void shouldRetrieveAllCorpora() {
        var corpusService = new CorpusService(targetWordRepository);
        Mockito.when(targetWordRepository.findAvailableCorpora()).thenReturn(ImmutableList.of(AMC_CORPUS));
        var corpora = corpusService.getAllCorpora();

        assertThat(corpora).isNotEmpty();
        assertThat(corpora.get(0)).isEqualTo(AMC_CORPUS);
    }
}