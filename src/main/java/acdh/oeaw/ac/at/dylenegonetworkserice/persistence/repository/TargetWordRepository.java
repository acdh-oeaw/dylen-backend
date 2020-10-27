package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TargetWordRepository extends MongoRepository<TargetWord, String> {
    public Optional<TargetWord> findById(String Id);
}
