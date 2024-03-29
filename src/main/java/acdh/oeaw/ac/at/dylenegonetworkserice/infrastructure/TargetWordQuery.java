package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class TargetWordQuery implements GraphQLQueryResolver {
    private final QueryServiceInterface queryService;

    public TargetWordQuery(QueryServiceInterface queryService) {
        this.queryService = queryService;
    }

    public TargetWord getTargetWordById(String corpus) {
        return queryService.getTargetWord(corpus);
    }
}
