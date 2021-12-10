package acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.targetWord.TargetWordNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord.AutocompleteRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord.TargetWordRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
        var pageRequest =  PageRequest.of(page, size);
        List<Suggestion> result = null;

        if (corpus.equals("ParlAT") && source.equals("-")) {
            result = autocompleteRepository.findByCorpusAndTextLike(corpus, searchTerm, pageRequest);
        } else {
            result = autocompleteRepository.findByCorpusAndSourceAndTextLike(corpus, source, searchTerm, pageRequest);
        }

        return result;
    }

    @Override
    public TargetWord getTargetWord(String id) {
        return targetWordRepository.findById(id).orElseThrow(()-> {
            throw new TargetWordNotFoundException("No ego network found with the id", id);
        });
    }
}
