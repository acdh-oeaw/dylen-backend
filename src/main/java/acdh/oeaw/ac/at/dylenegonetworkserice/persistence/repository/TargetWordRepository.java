package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TargetWordRepository extends MongoRepository<TargetWord, String> {
    public Optional<TargetWord> findById(String Id);

    public List<TargetWord> findByText(String text);

    public List<TargetWord> findByCorpusAndSource(String corpus, String source);

    public List<TargetWord> findBySource(String source);

    public List<TargetWord>findByCorpus(String corpus);
}
