package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService implements QueryServiceInterface {

    final TargetWordRepository targetWordRepository;

    public QueryService(TargetWordRepository targetWordRepository) {
        this.targetWordRepository = targetWordRepository;
    }

    @Override
    @Cacheable(value = "sources", cacheManager = "cacheMgr")
    public List<String> getSourcesByCorpus(String corpus) {
        return targetWordRepository.findSourceByCorpus(corpus);
    }

    @Override
    public List<TargetWord> getAutocompleteSuggestion(String corpus, String source, String searchTerm, int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        return targetWordRepository.findByCorpusAndSource(corpus, source, searchTerm, pageRequest);
    }
}
