package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Component
public class SearchQuery implements GraphQLQueryResolver {
    private final QueryServiceInterface queryService;

    public SearchQuery(QueryServiceInterface queryService) {
        this.queryService = queryService;
    }

    @CrossOrigin(origins = "https://dylen-tool.acdh.oeaw.ac.at")
    public List<Suggestion> getAutocompleteSuggestions(String corpus, String source, String searchTerm, int page, int size) {
        return queryService.getAutocompleteSuggestion(corpus, source, searchTerm, page, size);
    }
}
