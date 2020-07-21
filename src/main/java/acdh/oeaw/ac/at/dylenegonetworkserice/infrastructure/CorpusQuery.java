package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;


import acdh.oeaw.ac.at.dylenegonetworkserice.NetworkService;
import acdh.oeaw.ac.at.dylenegonetworkserice.SourceService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Source;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class CorpusQuery implements GraphQLQueryResolver {

    public static final String CORPUS_ID = "corpus-1";
    public static final String CORPUS_NAME = "AMC";
    public final List<Corpus> corpora;
    private final NetworkService networkService;

    public CorpusQuery(NetworkService networkService) throws IOException {
        var mapper = new ObjectMapper();

        var standard = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.STANDARD.getName(),
                networkService.getNetworkBySource(SourceService.SourceEnum.STANDARD.getName()));
        var krone = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.KRONE.getName(),
                networkService.getNetworkBySource(SourceService.SourceEnum.KRONE.getName()));
        var heute = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.HEUTE.getName(),
                networkService.getNetworkBySource(SourceService.SourceEnum.HEUTE.getName()));

        this.corpora = ImmutableList.of(Corpus.of(CORPUS_ID, CORPUS_NAME, ImmutableList.of(standard, krone, heute)));
        this.networkService = networkService;
    }

    public List<Corpus> getAllAvailableCorpora(){
        return this.corpora;

    }
}
