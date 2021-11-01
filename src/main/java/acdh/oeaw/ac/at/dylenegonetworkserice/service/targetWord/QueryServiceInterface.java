package acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;

import java.util.List;

public interface QueryServiceInterface {
    List<String> getSourcesByCorpus(String corpus);

    List<TargetWord> getAutocompleteSuggestion(String corpus, String source, String searchTerm, int page, int size);
}
