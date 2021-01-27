package acdh.oeaw.ac.at.dylenegonetworkservice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkservice.domain.TargetWord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TargetWordRepository extends MongoRepository<TargetWord, String> {
    Optional<TargetWord> findById(String Id);

    List<TargetWord> findByText(String text);

    List<TargetWord> findByCorpusAndSource(String corpus, String source);

    List<TargetWord>findByCorpus(String corpus);
}
