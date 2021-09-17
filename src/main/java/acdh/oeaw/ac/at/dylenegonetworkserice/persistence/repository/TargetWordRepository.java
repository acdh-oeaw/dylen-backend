package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface TargetWordRepository extends MongoRepository<TargetWord, String> {
    Optional<TargetWord> findById(String Id);

    List<TargetWord> findByText(String text);

    Slice<TargetWord> findByCorpusAndSource(String corpus, String source, Pageable pageable);

    List<TargetWord>findByCorpus(String corpus);

    @Aggregation(pipeline = { "{$match: {corpus:?0 }}", "{$group: {_id:\"$source\"}}" })
    List<String> findSourceByCorpus(String corpus);

    @Aggregation(pipeline = { "{$match: {}}", "{$group: {_id:\"$corpus\"}}" })
    List<String> findAvailableCorpora();
}
