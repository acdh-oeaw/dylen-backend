package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

//https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference
public interface EgoNetworkRepository extends MongoRepository<EgoNetwork, String>{
    public Optional<EgoNetwork> findById(String Id);
    public List<EgoNetwork> findByText(String text);
    public List<EgoNetwork> findEgoNetworksByCorpus(String corpus);
    public List<EgoNetwork> findAllByText(String text);
    public List<EgoNetwork> findByTextStartsWith(String chunk);
    public List<EgoNetwork> findBySource(String source);
    public List<EgoNetwork> findByCorpusAndSource(String corpus, String source);
}