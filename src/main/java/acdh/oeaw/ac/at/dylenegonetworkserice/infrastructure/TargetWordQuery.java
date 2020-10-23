package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.TargetWordDto;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.TargetWordService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TargetWordQuery implements GraphQLQueryResolver {
    private final TargetWordService targetWordService;

    public TargetWordQuery(TargetWordService targetWordService) {
        this.targetWordService = targetWordService;
    }

    public List<TargetWordDto> getAllAvailableTargetWords() {
        return targetWordService.getAllAvailableTargetWords();
    }
}
