package acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.SuggestionSliceDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QueryServiceInterface {
    List<String> getSourcesByCorpus(String corpus);

    List<Suggestion> getAutocompleteSuggestion(String corpus, String source, String searchTerm, int page, int size);

    TargetWord getTargetWord(String id);
}
