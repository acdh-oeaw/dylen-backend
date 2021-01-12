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
    private final CorpusService corpusService;

    public CorpusQuery(CorpusService corpusService) {

        this.corpusService = corpusService;
    }

    public List<Corpus> getAllAvailableCorpora(){
        return corpusService.getAllCorpora();
    }
}
