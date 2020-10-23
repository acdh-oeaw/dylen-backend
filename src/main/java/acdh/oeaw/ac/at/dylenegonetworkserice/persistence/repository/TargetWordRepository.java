package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TargetWordRepository extends MongoRepository<TargetWord, String> {
}
