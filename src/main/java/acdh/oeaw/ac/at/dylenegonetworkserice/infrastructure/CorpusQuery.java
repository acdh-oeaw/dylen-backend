package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;


import acdh.oeaw.ac.at.dylenegonetworkserice.CorpusService;
import acdh.oeaw.ac.at.dylenegonetworkserice.NetworkService;
import acdh.oeaw.ac.at.dylenegonetworkserice.SourceService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Source;
import com.google.common.collect.ImmutableList;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class CorpusQuery implements GraphQLQueryResolver {

    public static final String CORPUS_ID = "corpus-1";
    public static final String CORPUS_NAME = "AMC";
    public final List<Corpus> corpora;
    private final NetworkService networkService;
    private final CorpusService corpusService;

    public CorpusQuery(NetworkService networkService, CorpusService corpusService) {

        var standard = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.STANDARD.getName(),
                networkService.getNetworkBySource(SourceService.SourceEnum.STANDARD.getName()));
        var krone = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.KRONE.getName(),
                networkService.getNetworkBySource(SourceService.SourceEnum.KRONE.getName()));
        var heute = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.HEUTE.getName(),
                networkService.getNetworkBySource(SourceService.SourceEnum.HEUTE.getName()));

        this.corpora = ImmutableList.of(Corpus.of(CORPUS_ID, CORPUS_NAME, ImmutableList.of(standard, krone, heute)));
        this.networkService = networkService;
        this.corpusService = corpusService;
    }

    public List<Corpus> getAllAvailableCorpora(){
        return corpusService.getAllCorpora();
    }
}
