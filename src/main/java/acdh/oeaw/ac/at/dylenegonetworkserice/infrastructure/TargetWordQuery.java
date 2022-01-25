package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
public class TargetWordQuery implements GraphQLQueryResolver {
    private final QueryServiceInterface queryService;

    public TargetWordQuery(QueryServiceInterface queryService) {
        this.queryService = queryService;
    }

    @CrossOrigin(origins = "https://dylen-tool.acdh.oeaw.ac.at")
    public TargetWord getTargetWordById(String corpus) {
        return queryService.getTargetWord(corpus);
    }
}
