package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;


import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkService;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CorpusQuery implements GraphQLQueryResolver {

    public static final String CORPUS_ID = "corpus-1";
    public static final String CORPUS_NAME = "AMC";
    private final CorpusService corpusService;

    public CorpusQuery(EgoNetworkService networkService, CorpusService corpusService) {
        this.corpusService = corpusService;
    }

    public List<Corpus> getAllAvailableCorpora(){
        return corpusService.getAllCorpora();
    }
}
