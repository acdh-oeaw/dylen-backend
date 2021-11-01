package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord.QueryServiceInterface;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchQuery implements GraphQLQueryResolver {
    private final QueryServiceInterface queryService;

    public SearchQuery(QueryServiceInterface queryService) {
        this.queryService = queryService;
    }

    public List<TargetWord> getAutocompleteSuggestions(String corpus, String source, String searchTerm) {
        return queryService.getAutocompleteSuggestion(corpus, source, searchTerm, 0, 10);
    }
}
