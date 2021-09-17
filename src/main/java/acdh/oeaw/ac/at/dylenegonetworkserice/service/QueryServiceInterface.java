package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface QueryServiceInterface {
    List<String> getSourcesByCorpus(String corpus);

    List<TargetWord> getAutocompleteSuggestion(String corpus, String source, String searchTerm, int page, int size);
}
