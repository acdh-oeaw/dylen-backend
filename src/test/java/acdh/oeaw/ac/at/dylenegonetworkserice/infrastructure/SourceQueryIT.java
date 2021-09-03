package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SourceQueryIT {

    @Mock
    private TargetWordRepository targetWordRepository;

    @InjectMocks
    private QueryService queryService;


    @Test
    void shouldQuerySourcesByCorpus() {
        var query = new SourceQuery(queryService);
        when(targetWordRepository.findSourceByCorpus(TestFixture.AMC_CORPUS)).thenReturn(ImmutableList.of(TestFixture.AMC_CORPUS));

        var sources = query.getSourcesByCorpus(TestFixture.AMC_CORPUS);

        verify(targetWordRepository, times(1)).findSourceByCorpus(TestFixture.AMC_CORPUS);
        assertThat(sources.get(0)).isEqualTo(TestFixture.AMC_CORPUS);
    }
}