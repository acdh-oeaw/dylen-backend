package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Component
public class SourceQuery implements GraphQLQueryResolver {

    private final QueryServiceInterface queryService;

    public SourceQuery(QueryServiceInterface queryService) {
        this.queryService = queryService;
    }

    @CrossOrigin(origins = "https://dylen-tool.acdh.oeaw.ac.at")
    public List<String> getSourcesByCorpus(String corpus) {
        return queryService.getSourcesByCorpus(corpus);
    }
}
