package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusServiceInterface;
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

    public List<String> getAllAvailableCorpora(){
        return corpusService.getAllCorpora();
    }
}
