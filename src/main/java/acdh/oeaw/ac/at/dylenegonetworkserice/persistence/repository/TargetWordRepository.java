package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TargetWordRepository extends MongoRepository<TargetWord, String> {
    Optional<TargetWord> findById(String Id);

    List<TargetWord> findByText(String text);

    Slice<TargetWord> findByCorpusAndSource(String corpus, String source, Pageable pageable);

    List<TargetWord>findByCorpus(String corpus);

    @Aggregation(pipeline = { "{$match: {corpus:?0 }}", "{$group: {_id:\"$source\"}}" })
    List<String> findSourceByCorpus(String corpus);

    @Aggregation(pipeline = { "{$match: {}}", "{$group: {_id:\"$corpus\"}}" })
    List<String> findAvailableCorpora();

    @Query("{'corpus': ?0, 'source': ?1, $text: {$search: ?2}}")
    List<TargetWord> findByCorpusAndSource(String corpus, String source, String searchTerm, Pageable pageable);
}
