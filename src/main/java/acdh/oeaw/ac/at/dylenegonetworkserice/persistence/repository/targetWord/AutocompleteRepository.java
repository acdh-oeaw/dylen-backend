package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutocompleteRepository extends ElasticsearchRepository<Suggestion, String> {
     List<Suggestion> findByCorpusAndSourceAndTextLike(String corpus, String source, String text, Pageable pageable);
     List<Suggestion> findByCorpusAndTextLike(String corpus, String text, Pageable pageable);

}
