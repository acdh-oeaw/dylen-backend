package acdh.oeaw.ac.at.dylenegonetworkservice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkservice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkservice.service.CorpusServiceInterface;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Slf4j
public class CorpusQuery implements GraphQLQueryResolver {
    private final CorpusServiceInterface corpusService;

    public CorpusQuery(CorpusServiceInterface corpusService) {

        this.corpusService = corpusService;
    }

    public List<Corpus> getAllAvailableCorpora(){
        return corpusService.getAllCorpora();
    }
}
