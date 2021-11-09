package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutocompleteRepository extends ElasticsearchRepository<Suggestion, String> {

     List<Suggestion> findSuggestionByCorpusAndSourceAndTextLike(String corpus, String source, String text);
}
