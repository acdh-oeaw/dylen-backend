package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord.QueryServiceInterface;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SourceQuery implements GraphQLQueryResolver {

    private final QueryServiceInterface queryService;

    public SourceQuery(QueryServiceInterface queryService) {
        this.queryService = queryService;
    }

    public List<String> getSourcesByCorpus(String corpus) {
         return queryService.getSourcesByCorpus(corpus);
    }
}
