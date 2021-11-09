package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.TargetWordNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.AutocompleteRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryService implements QueryServiceInterface {

    final TargetWordRepository targetWordRepository;
    final AutocompleteRepository autocompleteRepository;

    public QueryService(TargetWordRepository targetWordRepository, AutocompleteRepository autocompleteRepository) {
        this.targetWordRepository = targetWordRepository;
        this.autocompleteRepository = autocompleteRepository;
    }

    @Override
    @Cacheable(value = "sources", cacheManager = "cacheMgr")
    public List<String> getSourcesByCorpus(String corpus) {
        return targetWordRepository.findSourceByCorpus(corpus);
    }

    @Override
    public List<Suggestion> getAutocompleteSuggestion(String corpus, String source, String searchTerm, int page, int size) {
        var hits = autocompleteRepository.findSuggestionByCorpusAndSourceAndTextLike(corpus, source, searchTerm);
        return hits;
    }

    @Override
    public TargetWord getTargetWord(String id) {
        return targetWordRepository.findById(id).orElseThrow(()-> {
            throw new TargetWordNotFoundException("No ego network found with the id", id);
        });
    }
}
