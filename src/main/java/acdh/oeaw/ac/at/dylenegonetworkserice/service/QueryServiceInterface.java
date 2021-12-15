package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;

import java.util.List;

public interface QueryServiceInterface {
    List<String> getSourcesByCorpus(String corpus);

    List<Suggestion> getAutocompleteSuggestion(String corpus, String source, String searchTerm, int page, int size);

    TargetWord getTargetWord(String id);
}
