package acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord.TargetWordRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
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
