package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.TargetWordDto;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.TargetWordService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class TargetWordQueryTest {
    @Mock
    TargetWordService targetWordService;

    @Disabled
    @Test
    void shouldReturnAllTargetWords() {
        var targetWordQuery = new TargetWordQuery(targetWordService);

        List<TargetWordDto> result = targetWordQuery.getAllAvailableTargetWords();

        assertThat(result).isNotEmpty();
    }
}